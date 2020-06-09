import React, {useState, useEffect} from "react";

function saveOrder(body){
    return window.fetch("http://localhost:8080/api/v1/orders/",{
        method:"POST",
        body: JSON.stringify(body),
        headers:{
            "content-type": "application/json;charset=UTF-8",
            "Authorization" : "Bearer "+localStorage.getItem('token'),        
        }
}).then(response => response.json());
}



const SaveOrder = () => {
    const [order,setOrders]= useState(null);
  
    const handleSubmit=event=>{
        event.preventDefault();
        const productsImput= event.target.elements.products;
        const clientImput= event.target.elements.client;
        const totalImput= event.target.elements.total;
        const discountImput= event.target.elements.discount;
        const statusImput= event.target.elements.status;       
        const body={
            products : productsImput.value,
            client : clientImput.value,          
            status: statusImput.value, 
            discount:discountImput.value,
            total:totalImput.value,  
        }        
        saveOrder(body).then(ordersData=> {setOrders(ordersData)});
    };

    return (
        <div className="col">        
        <form onSubmit={handleSubmit}>
          <h1>New Order</h1>
          <div className="form-group">
          <label>Products</label>		
          <input className="form-control" type="text" name="products"  maxLength="100"  required/>
          </div>		
          
          <div className="form-group">
          <label>Client</label>		
          <input className="form-control" type="text" name="client"   maxLength="280" required/>
          </div>		
          
          <div className="form-group">
          <label>Total</label>		
          <input className="form-control" type="number"  step="0.1" min="0"  name="total"  required/>
          </div>		
          
          <div className="form-group">
          <label>Discount</label>
          <input className="form-control" type="number"  min="0" max="100" name="discount"  required/>
          </div>		
          
          <div className="form-group">
          <label>Order Status</label>
          <div className="form-check">
            <div className="form-check" >
            <input className="form-check-input" type="radio" name="status" id="registrada" value="REGISTRADA" defaultChecked/>
            <label className="form-check-label" >
            REGISTRADA
            </label>
            </div>
            <div className="form-check">
            <input className="form-check-input" type="radio" name="status" id="pagada" value="PAGADA"  />
            <label className="form-check-label" >
            PAGADA
            </label>
            </div>
            <div className="form-check">
            <input className="form-check-input" type="radio" name="status" id="entregada" value="ENTREGADA"  />
            <label className="form-check-label" >
            ENTREGADA
            </label>
            </div>
          </div>
          </div>          		
          
          <hr/>
          
          <input type="submit" value="Save" className="btn btn-primary w-25" />
          </form>
          </div>          
    );
};

export default SaveOrder;