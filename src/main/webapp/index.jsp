<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
  <head>
    <meta charset="UTF-8">
    <title>Employee-Struts</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="index.css">
  </head>

  <body>
    <nav class="navbar navbar-default">
            <div class="container-fluid">
              <div class="navbar-header">
                <a href="#" class="navbar-brand" style="letter-spacing:2px">Employee CRUD </a>
              </div>
              <form class="navbar-form navbar-left">
                <div class="form-group">
                    <input type="text" id="search" placeholder="search" class="form-control"/>
                </div>
              </form>
            </div>
        </nav>
        <a class="nav-link" href="form.jsp?type=insert" style="text-decoration: none;font-size: 18px;">   
        <button class="btn btn-default" style="margin-left:15px;letter-spacing: 2px">
            Add Employee
        </button>  </a> 
        <div class="container">
            <table id="table" class="table table-bordered">
            </table>
        </div>
  </body>
   <script src="index.js"></script>
</html>

 