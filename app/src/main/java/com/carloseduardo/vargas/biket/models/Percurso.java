package com.carloseduardo.vargas.biket.models;

public class Percurso {

    private Long id;
    private Long id_rota;
    private Double longitude;
    private Double latitude;
    private String speed;

    public Percurso(){}

    public Percurso(Long id, Long id_rota, Double longitude, Double latitude, String speed) {
        this.id = id;
        this.id_rota = id_rota;
        this.longitude = longitude;
        this.latitude = latitude;
        this.speed = speed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_rota() {
        return id_rota;
    }

    public void setId_rota(Long id_rota) {
        this.id_rota = id_rota;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Percurso)) return false;

        Percurso percurso = (Percurso) o;

        if (getId() != null ? !getId().equals(percurso.getId()) : percurso.getId() != null)
            return false;
        if (getId_rota() != null ? !getId_rota().equals(percurso.getId_rota()) : percurso.getId_rota() != null)
            return false;
        if (getLongitude() != null ? !getLongitude().equals(percurso.getLongitude()) : percurso.getLongitude() != null)
            return false;
        return getLatitude() != null ? getLatitude().equals(percurso.getLatitude()) : percurso.getLatitude() == null;

    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getId_rota() != null ? getId_rota().hashCode() : 0);
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
        result = 31 * result + (getLatitude() != null ? getLatitude().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Percurso{" +
                "id=" + id +
                ", id_rota=" + id_rota +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", speed='" + speed + '\'' +
                '}';
    }
}
