import React, {useState, useEffect} from "react";
import { makeStyles, withStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {Link} from '@reach/router';

const StyledTableCell = withStyles((theme) => ({
    head: {
      backgroundColor: theme.palette.common.black,
      color: theme.palette.common.white,
    },
    body: {
      fontSize: 14,
    },
  }))(TableCell);

  const useStyles = makeStyles({
    table: {
      minWidth: 650,
    },
  });



function productsInfo(){
    return window.fetch("http://localhost:8080/api/v1/products/",{
        method:"GET",                   
        headers:{
            "content-type": "application/json;charset=UTF-8",
            "Accept" : "application/json", 
            "Authorization" : "Bearer "+localStorage.getItem('token'),
        }
}).then(response => response.json());
}

function productDelete(id){
  return window.fetch("http://localhost:8080/api/v1/products/"+id,{
      method:"DELETE",                   
      headers:{
          "content-type": "application/json;charset=UTF-8",
          "Accept" : "application/json",
          "Authorization" : "Bearer "+localStorage.getItem('token'),           
      }
}).then(response => response.json());
}

const useProducts = () => {
    const [products,setProducts]= useState([]);
    useEffect(()=>{
        productsInfo().then(productsData=> {setProducts(productsData)});
    },[setProducts]);

    return {products};
};



const ProductsInfo = () => {
    const {products}= useProducts();   
    const classes = useStyles();   

    return (
      <TableContainer component={Paper}>
        <Table className={classes.table} size="small" aria-label="a dense table">
          <TableHead>
            <TableRow>
              <StyledTableCell>Id</StyledTableCell>
              <StyledTableCell align="right">Name</StyledTableCell>
              <StyledTableCell align="right">Description</StyledTableCell>
              <StyledTableCell align="right">BasePrice</StyledTableCell>
              <StyledTableCell align="right">TaxRate</StyledTableCell>
              <StyledTableCell align="right">Status</StyledTableCell>
              <StyledTableCell align="right">Quantity</StyledTableCell>
              <StyledTableCell align="center">Accion</StyledTableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {products.map((row) => (
              <TableRow key={row.id}>
                <TableCell component="th" scope="row">
                  {row.id}
                </TableCell>
                <TableCell align="right">{row.name}</TableCell>
                <TableCell align="right">{row.description}</TableCell>
                <TableCell align="right">{row.basePrice}</TableCell>
                <TableCell align="right">{row.taxRate}</TableCell>
                <TableCell align="right">{row.productStatus}</TableCell>
                <TableCell align="right">{row.inventoryQueantity}</TableCell> 
                <TableCell align="right"><div className="btn-group" role="group" aria-label="Basic example">
 <Link to={`/updateproduct/`+row.id}> <button type="button" className="btn btn-primary">Edit</button> </Link>
  <button type="button" className="btn btn-danger" onClick={()=>productDelete(row.id)}>Delete</button>  
</div></TableCell>                
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    );
       
        
    
};

export default ProductsInfo;
