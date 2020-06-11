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



function ordersInfo(){
    return window.fetch("http://localhost:8080/api/v1/orders/",{
        method:"GET",                   
        headers:{
            "content-type": "application/json;charset=UTF-8",
            "Accept" : "*/*", 
            "Authorization" : "Bearer "+localStorage.getItem('token'),
        }
}).then(response => response.json());
}

function orderDelete(id){
  return window.fetch("http://localhost:8080/api/v1/orders/"+id,{
      method:"DELETE",                   
      headers:{
          "content-type": "application/json;charset=UTF-8",
          "Accept" : "application/json",
          "Authorization" : "Bearer "+localStorage.getItem('token'),           
      }
}).then(response => response.json());
}

const useOrders = () => {
    const [orders,setOrders]= useState([]);
    useEffect(()=>{
        ordersInfo().then(ordersData=> {setOrders(ordersData)});
    },[setOrders]);

    return {orders};
};



const OrdersInfo = () => {
    const {orders}= useOrders();   
    const classes = useStyles();      

    return (
      <TableContainer component={Paper}>
        <Table className={classes.table} size="small" aria-label="a dense table">
          <TableHead>
            <TableRow>
              <StyledTableCell>Id</StyledTableCell>
              <StyledTableCell align="right">Products</StyledTableCell>
              <StyledTableCell align="right">Client</StyledTableCell>
              <StyledTableCell align="right">Total</StyledTableCell>
              <StyledTableCell align="right">Discount</StyledTableCell>
              <StyledTableCell align="right">Status</StyledTableCell> 
              <StyledTableCell align="center">Action</StyledTableCell>           
            </TableRow>
          </TableHead>
          <TableBody>
            {orders.map((row) => (
              <TableRow key={row.id}>
                <TableCell component="th" scope="row">
                  {row.id}
                </TableCell>
                <TableCell align="right">{row.products}</TableCell>
                <TableCell align="right">{row.client}</TableCell>
                <TableCell align="right">{row.total}</TableCell>
                <TableCell align="right">{row.discount}</TableCell>
                <TableCell align="right">{row.status}</TableCell>                 
                <TableCell align="right"><div className="btn-group" role="group" aria-label="Basic example">
 <Link to={`/updateorder/`+row.id}> <button type="button" className="btn btn-primary">Edit</button> </Link>
  <button type="button" className="btn btn-danger" onClick={()=>orderDelete(row.id)}>Delete</button>  
</div></TableCell>                
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    );
       
        
    
};

export default OrdersInfo;
