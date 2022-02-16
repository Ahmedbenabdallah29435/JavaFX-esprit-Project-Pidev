/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Sponsor;
import entity.Evennement;
import java.util.List;

/**
 *
 * @author AhmedBenAbdallah
 */
public interface IService<T> {
    public void ajouter(T t);
    public void supprimer(int id);
    public void modifier(int id_amodifier,T modifier);
    public List<T> afficher();
    public List<T> afficherById(int id);
    
}
