import React from "react";
import { KeycloakProvider } from "@react-keycloak/web";
import keycloak, { config as keyCloakProps } from "./keycloak";
import { Router } from '@reach/router';
import AllProducts from './products/index';
import SaveProduct from './products/saveProducts';
import UpdateProduct from './products/updateProduct';
import AllOrders from './orders/index';
import SaveOrder from './orders/saveOrder';
import UpdateOrder from './orders/updateOrder';
import { Link } from '@reach/router';

import "./App.css";

function App() {
  return (
    <div>
    <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
    <Link to={`/`}> <a className="navbar-brand" href="#">I.A.S</a></Link>
    <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span className="navbar-toggler-icon"></span>
    </button>
    <div className="collapse navbar-collapse" id="navbarNav">
      <ul className="navbar-nav">
        <li className="nav-item active">
          <Link to={`/products`}> <a className="nav-link" href="/products">Products <span className="sr-only">(current)</span></a> </Link>
        </li>
        <li className="nav-item active">
         <Link to={`/orders`}> <a className="nav-link" href="/orders">Orders<span className="sr-only">(current)</span></a></Link>
        </li>
      </ul>
    </div>
  </nav>
    <KeycloakProvider keycloak={keycloak} {...keyCloakProps}>
     
      <div>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossOrigin="anonymous"></link>
        <Router>         
          <AllProducts path="products" />
          <SaveProduct path="saveproduct" />
          <UpdateProduct path="updateproduct/:id" />
          <AllOrders path="orders" />
          <SaveOrder path="saveorder" />
          <UpdateOrder path="updateorder/:id" />

        </Router>

      </div>
    </KeycloakProvider>
    </div>
  );
}

export default App;
