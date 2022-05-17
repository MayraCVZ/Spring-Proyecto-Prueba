// Call the dataTables jQuery plugin
$(document).ready(function() {
  cargarUsuarios();
  $('#usuarios').DataTable();
  actualizarEmailUsuario();
});
function actualizarEmailUsuario(){
document.getElementById("txt-email-usuario").outerHTML=localStorage.email;
}

async function cargarUsuarios(){
  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers: getHeaders()
  });
  const usuarios = await request.json();

  console.log(usuarios);
  let listadoUsuariosHtml='';
  for (let usuario of usuarios){
    let btnEliminar='<a href="#" onclick="eliminarUsuario('+usuario.id+')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';

    let usuarioHtml = '  <tr><td>'+usuario.id+'</td> <td>'+usuario.nombre
        +'</td><td>'+usuario.email
        +'</td><td>'+usuario.telefono
        +'</td><td>'+btnEliminar+'</td> </tr>'

    listadoUsuariosHtml+=usuarioHtml;
}
  document.querySelector('#usuarios tbody').outerHTML=listadoUsuariosHtml;
}
function getHeaders() {
    return {
     'Accept': 'application/json',
     'Content-Type': 'application/json',
     'Authorization': localStorage.token
   };
}
async function eliminarUsuario(id){
   if(!confirm('Desea eliminar este usuario?')){
    return;
    }
   await fetch('api/usuarios/'+id, {
    method: 'DELETE',
    headers: getHeaders()
    });
    location.reload();
}