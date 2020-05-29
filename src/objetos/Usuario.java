package objetos;

public class Usuario {
String username;
String password;
String nombre;
String correo;
String foto;
String tipo;
public Usuario(String username, String password, String nombre, String correo, String tipo) {
	super();
	this.username = username;
	this.password = password;
	this.nombre = nombre;
	this.correo = correo;
	this.tipo = tipo;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getFoto() {
	return foto;
}
public void setFoto(String foto) {
	this.foto = foto;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}
public String getUsername() {
	return username;
}
public String getCorreo() {
	return correo;
}
@Override
public String toString() {
	return "Usuario [username=" + username + ", password=" + password + ", nombre=" + nombre + ", correo=" + correo
			+ ", foto=" + foto + ", tipo=" + tipo + "]";
}
}
