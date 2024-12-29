/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.method.tubesaka;

import java.util.List;

/**
 *
 * @author user
 */
public class Makanan {
    private String namaMakanan;
    private List<String> bahan;
    private List<String> alergen;

    public Makanan(String namaMakanan, List<String> bahan, List<String> alergen) {
        this.namaMakanan = namaMakanan;
        this.bahan = bahan;
        this.alergen = alergen;
    }

    public String getNamaMakanan() {
        return namaMakanan;
    }

    public List<String> getBahan() {
        return bahan;
    }

    public List<String> getAlergen() {
        return alergen;
    }
    
}
