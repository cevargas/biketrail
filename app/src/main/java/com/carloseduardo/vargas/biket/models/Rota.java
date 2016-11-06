package com.carloseduardo.vargas.biket.models;

import java.util.Date;

public class Rota {

    private Long id;
    private Date data;
    private int situacao;
    private Double totalPercurso;
    private boolean sync;
    private Date dataHoraSync;
    private Double startLongitude;
    private Double endLongitude;
    private Double startLatitude;
    private Double endLatitude;

    private Percurso percurso;

    public Rota() {
    }

    public Rota(Long id, Date data, int situacao, Double totalPercurso, boolean sync, Date dataHoraSync, Double startLongitude, Double endLongitude, Double startLatitude, Double endLatitude, Percurso percurso) {
        this.id = id;
        this.data = data;
        this.situacao = situacao;
        this.totalPercurso = totalPercurso;
        this.sync = sync;
        this.dataHoraSync = dataHoraSync;
        this.startLongitude = startLongitude;
        this.endLongitude = endLongitude;
        this.startLatitude = startLatitude;
        this.endLatitude = endLatitude;
        this.percurso = percurso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }

    public Double getTotalPercurso() {
        return totalPercurso;
    }

    public void setTotalPercurso(Double totalPercurso) {
        this.totalPercurso = totalPercurso;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }

    public Date getDataHoraSync() {
        return dataHoraSync;
    }

    public void setDataHoraSync(Date dataHoraSync) {
        this.dataHoraSync = dataHoraSync;
    }

    public Double getStartLongitude() {
        return startLongitude;
    }

    public void setStartLongitude(Double startLongitude) {
        this.startLongitude = startLongitude;
    }

    public Double getEndLongitude() {
        return endLongitude;
    }

    public void setEndLongitude(Double endLongitude) {
        this.endLongitude = endLongitude;
    }

    public Double getStartLatitude() {
        return startLatitude;
    }

    public void setStartLatitude(Double startLatitude) {
        this.startLatitude = startLatitude;
    }

    public Double getEndLatitude() {
        return endLatitude;
    }

    public void setEndLatitude(Double endLatitude) {
        this.endLatitude = endLatitude;
    }

    public Percurso getPercurso() {
        return percurso;
    }

    public void setPercurso(Percurso percurso) {
        this.percurso = percurso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rota)) return false;

        Rota rota = (Rota) o;

        if (getSituacao() != rota.getSituacao()) return false;
        if (isSync() != rota.isSync()) return false;
        if (!getId().equals(rota.getId())) return false;
        if (getData() != null ? !getData().equals(rota.getData()) : rota.getData() != null)
            return false;
        if (getTotalPercurso() != null ? !getTotalPercurso().equals(rota.getTotalPercurso()) : rota.getTotalPercurso() != null)
            return false;
        return getDataHoraSync() != null ? getDataHoraSync().equals(rota.getDataHoraSync()) : rota.getDataHoraSync() == null;

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getData() != null ? getData().hashCode() : 0);
        result = 31 * result + getSituacao();
        result = 31 * result + (getTotalPercurso() != null ? getTotalPercurso().hashCode() : 0);
        result = 31 * result + (isSync() ? 1 : 0);
        result = 31 * result + (getDataHoraSync() != null ? getDataHoraSync().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Rota{" +
                "id=" + id +
                ", data=" + data +
                ", situacao=" + situacao +
                ", totalPercurso=" + totalPercurso +
                ", sync=" + sync +
                ", dataHoraSync=" + dataHoraSync +
                ", startLongitude=" + startLongitude +
                ", endLongitude=" + endLongitude +
                ", startLatitude=" + startLatitude +
                ", endLatitude=" + endLatitude +
                ", percurso=" + percurso +
                '}';
    }
}
