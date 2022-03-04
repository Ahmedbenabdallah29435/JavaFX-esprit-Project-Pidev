/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.util.List;

/**
 *
 *
 */
public interface ICategorie <C>  {
    
    public boolean AjouterCategorie(C c);
    public boolean ModifierCategorie(C c);
    public boolean SupprimerCategorie(int idCategorie);
    public List<C> AfficherCategorie(C c);
    public int countTotalCatgeorie();
}
