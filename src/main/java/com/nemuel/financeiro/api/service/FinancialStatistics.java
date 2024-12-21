package com.nemuel.financeiro.api.service;

import java.util.Objects;

public class FinancialStatistics {
    private double totalReceitas;
    private double totalDespesas;
    private double mediaReceitasMensal;
    private double mediaDespesasMensal;
    private double medianaReceitas;
    private double medianaDespesas;
    private double modaReceitas;
    private double modaDespesas;
    private double desvioPadraoReceitas;
    private double desvioPadraoDespesas;

    // Construtor que inicializa todos os campos
    public FinancialStatistics(double totalReceitas, double totalDespesas, double mediaReceitasMensal,
                                double mediaDespesasMensal, double medianaReceitas, double medianaDespesas,
                                double modaReceitas, double modaDespesas, double desvioPadraoReceitas,
                                double desvioPadraoDespesas) {
        this.totalReceitas = totalReceitas;
        this.totalDespesas = totalDespesas;
        this.mediaReceitasMensal = mediaReceitasMensal;
        this.mediaDespesasMensal = mediaDespesasMensal;
        this.medianaReceitas = medianaReceitas;
        this.medianaDespesas = medianaDespesas;
        this.modaReceitas = modaReceitas;
        this.modaDespesas = modaDespesas;
        this.desvioPadraoReceitas = desvioPadraoReceitas;
        this.desvioPadraoDespesas = desvioPadraoDespesas;
    }

    // Getters e Setters (opcionalmente automáticos para todos os campos)
    public double getTotalReceitas() {
        return totalReceitas;
    }

    public void setTotalReceitas(double totalReceitas) {
        this.totalReceitas = totalReceitas;
    }

    public double getTotalDespesas() {
        return totalDespesas;
    }

    public void setTotalDespesas(double totalDespesas) {
        this.totalDespesas = totalDespesas;
    }

    public double getMediaReceitasMensal() {
        return mediaReceitasMensal;
    }

    public void setMediaReceitasMensal(double mediaReceitasMensal) {
        this.mediaReceitasMensal = mediaReceitasMensal;
    }

    public double getMediaDespesasMensal() {
        return mediaDespesasMensal;
    }

    public void setMediaDespesasMensal(double mediaDespesasMensal) {
        this.mediaDespesasMensal = mediaDespesasMensal;
    }

    public double getMedianaReceitas() {
        return medianaReceitas;
    }

    public void setMedianaReceitas(double medianaReceitas) {
        this.medianaReceitas = medianaReceitas;
    }

    public double getMedianaDespesas() {
        return medianaDespesas;
    }

    public void setMedianaDespesas(double medianaDespesas) {
        this.medianaDespesas = medianaDespesas;
    }

    public double getModaReceitas() {
        return modaReceitas;
    }

    public void setModaReceitas(double modaReceitas) {
        this.modaReceitas = modaReceitas;
    }

    public double getModaDespesas() {
        return modaDespesas;
    }

    public void setModaDespesas(double modaDespesas) {
        this.modaDespesas = modaDespesas;
    }

    public double getDesvioPadraoReceitas() {
        return desvioPadraoReceitas;
    }

    public void setDesvioPadraoReceitas(double desvioPadraoReceitas) {
        this.desvioPadraoReceitas = desvioPadraoReceitas;
    }

    public double getDesvioPadraoDespesas() {
        return desvioPadraoDespesas;
    }

    public void setDesvioPadraoDespesas(double desvioPadraoDespesas) {
        this.desvioPadraoDespesas = desvioPadraoDespesas;
    }
}
