
package service;

import entity.Evennement;
import java.util.List;


//public interface IService<T> {
//    public void ajouter(T t);
//    public void supprimer(int id);
//    public void modifier(int id_amodifier,T modifier);
//    public List<T> afficher();
//}
public interface IService<T> {

   boolean ajouter(T t);
   boolean supprimer(T t);
   boolean modifier(T t);
   public List<T> afficher();
   T afficher_id(int id);
}
