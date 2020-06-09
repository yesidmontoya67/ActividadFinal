import React, { useState } from "react";
import OrdersInfo from './allOrders';
import {Link} from '@reach/router';


const AllOrders = () => {
    const [value,setValue]= useState([]);
   return ( 
   <div>
    <div className="col text-right">
        <Link to={`/saveorder`} >
        <button  className="btn btn-primary">           
            Add Order
        </button>
        </Link>
    </div>


    <div>        
        <form >
            <h1>
                All Orders
            </h1>
        </form>
        <OrdersInfo/>       
        
    </div>

    </div>
    );
};

export default AllOrders;