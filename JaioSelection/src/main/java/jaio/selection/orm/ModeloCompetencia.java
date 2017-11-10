package jaio.selection.orm;
// Generated 06/11/2017 06:07:26 PM by Hibernate Tools 5.1.4.Final


import java.util.HashSet;
import java.util.Set;

/**
 * ModeloCompetencia generated by hbm2java
 */
public class ModeloCompetencia  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private String resena;
     private Set modeloCompetenciaSinonimos = new HashSet(0);
     private Set modeloEvaluacionXCompetencias = new HashSet(0);
     private Set modeloCompetenciaSinonimos_1 = new HashSet(0);
     private Set modeloEvaluacionXCompetencias_1 = new HashSet(0);

    public ModeloCompetencia() {
    }

	
    public ModeloCompetencia(String nombre) {
        this.nombre = nombre;
    }
    public ModeloCompetencia(String nombre, String resena, Set modeloCompetenciaSinonimos, Set modeloEvaluacionXCompetencias, Set modeloCompetenciaSinonimos_1, Set modeloEvaluacionXCompetencias_1) {
       this.nombre = nombre;
       this.resena = resena;
       this.modeloCompetenciaSinonimos = modeloCompetenciaSinonimos;
       this.modeloEvaluacionXCompetencias = modeloEvaluacionXCompetencias;
       this.modeloCompetenciaSinonimos_1 = modeloCompetenciaSinonimos_1;
       this.modeloEvaluacionXCompetencias_1 = modeloEvaluacionXCompetencias_1;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getResena() {
        return this.resena;
    }
    
    public void setResena(String resena) {
        this.resena = resena;
    }
    public Set getModeloCompetenciaSinonimos() {
        return this.modeloCompetenciaSinonimos;
    }
    
    public void setModeloCompetenciaSinonimos(Set modeloCompetenciaSinonimos) {
        this.modeloCompetenciaSinonimos = modeloCompetenciaSinonimos;
    }
    public Set getModeloEvaluacionXCompetencias() {
        return this.modeloEvaluacionXCompetencias;
    }
    
    public void setModeloEvaluacionXCompetencias(Set modeloEvaluacionXCompetencias) {
        this.modeloEvaluacionXCompetencias = modeloEvaluacionXCompetencias;
    }
    public Set getModeloCompetenciaSinonimos_1() {
        return this.modeloCompetenciaSinonimos_1;
    }
    
    public void setModeloCompetenciaSinonimos_1(Set modeloCompetenciaSinonimos_1) {
        this.modeloCompetenciaSinonimos_1 = modeloCompetenciaSinonimos_1;
    }
    public Set getModeloEvaluacionXCompetencias_1() {
        return this.modeloEvaluacionXCompetencias_1;
    }
    
    public void setModeloEvaluacionXCompetencias_1(Set modeloEvaluacionXCompetencias_1) {
        this.modeloEvaluacionXCompetencias_1 = modeloEvaluacionXCompetencias_1;
    }




}


