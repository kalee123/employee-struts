// fetching initial employee data
window.onload =async()=>{
    let {empList} = await fetch('search',{
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({'param':""})
    }).then(res => res.json());
    let table = await tables(empList);
    document.getElementById("table").innerHTML=table;
};
 
// fetching employee data based on search param
let search = document.querySelector('#search');
search.addEventListener('keyup', async function(e) {
  let param = e.target.value;
  
  let {empList} = await fetch('search',{
    method: 'POST',
    headers: {
       'Accept': 'application/json',
      'Content-Type': 'application/json'
    }  ,
    body: JSON.stringify({'param': param})
  }).then(res => res.json());
  let table = await tables(empList);
  document.getElementById("table").innerHTML=table;
	  
});

document.addEventListener('click',async function(e){
  if(e.target.innerHTML==="DELETE"){
    let {empList} =await fetch('delete?'+ new URLSearchParams({id:e.target.id}),{
      method: 'GET',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      }
    }).then(res => res.json());
    let table = await tables(empList);
    document.getElementById("table").innerHTML=table;
  }
  
});


const tables =(data)=>{
    let tdata = `<tr>
                    <th>Id</th>
                    <th>fname</th>
                    <th>lname</th>
                    <th>email</th>
                    <th>role</th>
                    <th>city</th>
                    <th>actions</th>
                </tr>`
            
    for(let i=0;i<data.length;i++){
      tdata += `<tr>
                  <td>${data[i].id}</td>
                  <td>${data[i].firstName}</td>
                  <td>${data[i].lastName}</td>
                  <td>${data[i].email}</td>
                  <td>${data[i].role}</td>
                  <td>${data[i].city}</td>
                  <td>
                    <button class="btn btn-primary">
                      <a href="form.jsp?type=update&id=${data[i].id}" style="color:white">EDIT</a>
                    </button>
                    <button id="${data[i].id}" class="btn btn-primary">DELETE</button>
                  </td>
                </tr>`
    }
    return tdata;
}