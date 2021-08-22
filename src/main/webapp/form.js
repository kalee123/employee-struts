window.onload = async()=>{
  let url_str = window.location.href;
  let url = new URL(url_str);
  let type = url.searchParams.get("type");
  let id = url.searchParams.get("id");
  if(id){
    let {empDetail} = await fetch('editData',{
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          'type':'update',
          'id': id
        })
      }).then(res => res.json());
      let {employee,address} = empDetail;
      document.getElementById("id").value=employee.id;
      document.getElementById("firstname").value=employee.firstName;
      document.getElementById("lastname").value=employee.lastName;
      document.getElementById("email").value=employee.email;
      document.getElementById("dob").value=employee.dob;
      document.getElementById("role").value=employee.role;

      document.getElementById("door").value=address.doorNo;
      document.getElementById("street").value=address.street;
      document.getElementById("city").value=address.city;
  }
  else{
    let {id}=await fetch('editData',{
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          'type':'insert'
        })
      }).then(res => res.json());
      document.getElementById("id").value=id;
  }
}