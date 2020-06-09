import React, { useState } from "react";
import ProductsInfo from './allProducts';
import {Link} from '@reach/router';


const AllProducts = () => {
    const [value,setValue]= useState([]);
   return ( 
   <div>
    <div className="col text-right">
        <Link to={`/saveproduct`} >
        <button  className="btn btn-primary">           
            Add Product
        </button>
        </Link>
    </div>


    <div>        
        <form >
            <h1>
                All Products
            </h1>
        </form>
        <ProductsInfo/>       
        
    </div>

    </div>
    );
};

export default AllProducts;