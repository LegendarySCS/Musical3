package zambanosuarez.musical.DataConexion;

class DatosCA  {
   private  int id;
   private String  titulo;
    private String nombre;
    private String username;
    private String mail;
    private String password;
    private String created_at;
    private String  deleted_at;
    private String updated_at;
    private String  tipo_usuario_id;
    private String remember_token;
    private String archivo_img;
 private String tipo;

    private String descripcion;

 public DatosCA(int id, String nombre, String username, String mail, String password, String created_at, String deleted_at, String updated_at, String tipo_usuario_id, String remember_token, String archivo_img,String tipo, String descripcion) {
  this.id = id;

  this.nombre = nombre;
  this.username = username;
  this.mail = mail;
  this.password = password;
  this.created_at = created_at;
  this.deleted_at = deleted_at;
  this.updated_at = updated_at;
  this.tipo_usuario_id = tipo_usuario_id;
  this.remember_token = remember_token;
  this.archivo_img = archivo_img;
  this.tipo=tipo;
  this.descripcion = descripcion;
 }

 public int getId() {
  return id;
 }

 public void setId(int id) {
  this.id = id;
 }

 public String getTitulo() {
  return titulo;
 }

 public void setTitulo(String titulo) {
  this.titulo = titulo;
 }

 public String getNombre() {
  return nombre;
 }

 public void setNombre(String nombre) {
  this.nombre = nombre;
 }

 public String getUsername() {
  return username;
 }

 public void setUsername(String username) {
  this.username = username;
 }

 public String getMail() {
  return mail;
 }

 public void setMail(String mail) {
  this.mail = mail;
 }

 public String getPassword() {
  return password;
 }

 public void setPassword(String password) {
  this.password = password;
 }

 public String getCreated_at() {
  return created_at;
 }

 public void setCreated_at(String created_at) {
  this.created_at = created_at;
 }

 public String getDeleted_at() {
  return deleted_at;
 }

 public void setDeleted_at(String deleted_at) {
  this.deleted_at = deleted_at;
 }

 public String getUpdated_at() {
  return updated_at;
 }

 public void setUpdated_at(String updated_at) {
  this.updated_at = updated_at;
 }

 public String getTipo_usuario_id() {
  return tipo_usuario_id;
 }

 public void setTipo_usuario_id(String tipo_usuario_id) {
  this.tipo_usuario_id = tipo_usuario_id;
 }

 public String getRemember_token() {
  return remember_token;
 }

 public void setRemember_token(String remember_token) {
  this.remember_token = remember_token;
 }

 public String getArchivo_img() {
  return archivo_img;
 }

 public void setArchivo_img(String archivo_img) {
  this.archivo_img = archivo_img;
 }

 public String getDescripcion() {
  return descripcion;
 }

 public void setDescripcion(String descripcion) {
  this.descripcion = descripcion;
 }

 public String getTipo() {
  return tipo;
 }

 public void setTipo(String tipo) {
  this.tipo = tipo;
 }
}
