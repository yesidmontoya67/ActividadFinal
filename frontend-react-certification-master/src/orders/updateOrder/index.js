import React, {useState, useEffect} from "react";

function updateOrder(body,id){
    return window.fetch("http://localhost:8080/api/v1/orders/"+id,{
        method:"PUT",
        body: JSON.stringify(body),
        headers:{
            "content-type": "application/json;charset=UTF-8",
             "Authorization" : "Bearer "+localStorage.getItem('token'),        
        }
}).then(response => response.json());
}

function getOrder(id){
    return window.fetch("http://localhost:8080/api/v1/orders/"+id,{
        method:"GET",                   
        headers:{
            "content-type": "application/json;charset=UTF-8",
            "Authorization" : "Bearer "+localStorage.getItem('token'),          
        }
  }).then(response =>  response.json());
  }

  
  const useOrders = (id) => {
          const [orders,setOrders]= useState([]);
    useEffect(()=>{
        getOrder(id).then(ordersData=> {setOrders(ordersData)});
    },[setOrders]);    

    return {orders};
};




const UpdateOrder = props => {
    const [order,setOrders]= useState(null);     
    const {orders}= useOrders(props.id);      

      function checked (e) {        
        if (e === "REGISTRADA"){
            return false; }
        else if (e === "PAGADA") {
            return true;   }   
        else {
            return undefined
        }
                 
    }  
    
    function checked2 (e) {        
        if (e === "ENTREGADA"){
            return true; }
        else {
            return false;   }   
                 
    }    

    const [selectedValue, setSelectedValue] = useState(orders.status);

    const handleChange = (event) => { 
        orders.status= event.target.value;     
        setSelectedValue(event.target.value);
      }; 
      
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
        updateOrder(body,props.id).then(ordersData=> {setOrders(ordersData)});
    };    

    return (
        <div className="col">                
        <form onSubmit={handleSubmit}>
        <h1>Order {orders.id}</h1>
          <div className="form-group">
          <label>Products</label>		
          <input className="form-control" type="text" name="products"  maxLength="100"  defaultValue={orders.products}  required/>
          </div>		
          
          <div className="form-group">
          <label>Client</label>		
          <input className="form-control" type="text" name="client"   maxLength="280" defaultValue={orders.client} required/>
          </div>		
          
          <div className="form-group">
          <label>Total</label>		
          <input className="form-control" type="number"  step="0.1" min="0"  name="total" defaultValue={orders.total} required/>
          </div>		
          
          <div className="form-group">
          <label>Discount</label>
          <input className="form-control" type="number"  min="0" max="100" name="discount" defaultValue={orders.discount} required/>
          </div>		
          
          <div className="form-group">
          <label>Order Status</label>
          <div className="form-check">
            <div className="form-check" >
            <input className="form-check-input" type="radio" name="status" id="registrada" value="REGISTRADA" onChange={handleChange} defaultChecked= {!checked(orders.status)}/>
            <label className="form-check-label" >
            REGISTRADA
            </label>
            </div>
            <div className="form-check">
            <input className="form-check-input" type="radio" name="status" id="pagada" value="PAGADA" onChange={handleChange} defaultChecked= {checked(orders.status)} />
            <label className="form-check-label" >
            PAGADA
            </label>
            </div>
            <div className="form-check">
            <input className="form-check-input" type="radio" name="status" id="entregada" value="ENTREGADA"  onChange={handleChange} checked= {checked2(orders.status)} />
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

export default UpdateOrder;