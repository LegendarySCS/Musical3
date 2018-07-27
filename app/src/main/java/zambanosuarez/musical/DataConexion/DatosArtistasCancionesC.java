package zambanosuarez.musical.DataConexion;

public class DatosArtistasCancionesC {
    private int id;
    private String titulo;
    private String genero_id;
    private String cantante_id;
    private String descripcion;
    private String created_at;
    private String updated_at;
    private String deleted_at;
    private String archivo;
    private String imagen_cancion;
    private String genero;

    public DatosArtistasCancionesC(int id, String titulo, String genero_id, String cantante_id, String descripcion, String created_at, String updated_at, String deleted_at, String archivo, String imagen_cancion) {
        this.id = id;
        this.titulo = titulo;
        this.genero_id = genero_id;
        this.cantante_id = cantante_id;
        this.descripcion = descripcion;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.deleted_at = deleted_at;
        this.archivo = archivo;
        this.imagen_cancion = imagen_cancion;


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

    public String getGenero_id() {
        return genero_id;
    }

    public void setGenero_id(String genero_id) {
        this.genero_id = genero_id;
    }

    public String getCantante_id() {
        return cantante_id;
    }

    public void setCantante_id(String cantante_id) {
        this.cantante_id = cantante_id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getDeleted_at() {
        return deleted_at;
    }

    public void setDeleted_at(String deleted_at) {
        this.deleted_at = deleted_at;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getImagen_cancion() {
        return imagen_cancion;
    }

    public void setImagen_cancion(String imagen_cancion) {
        this.imagen_cancion = imagen_cancion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
