package jaio.selection.orm;
// Generated 06/11/2017 06:07:26 PM by Hibernate Tools 5.1.4.Final



/**
 * ModeloEvaluacionXCompetenciaId generated by hbm2java
 */
public class ModeloEvaluacionXCompetenciaId  implements java.io.Serializable {


     private int modeloEvaluacionId;
     private int modeloCompetenciaId;
     private byte esExacto;

    public ModeloEvaluacionXCompetenciaId() {
    }

    public ModeloEvaluacionXCompetenciaId(int modeloEvaluacionId, int modeloCompetenciaId, byte esExacto) {
       this.modeloEvaluacionId = modeloEvaluacionId;
       this.modeloCompetenciaId = modeloCompetenciaId;
       this.esExacto = esExacto;
    }
   
    public int getModeloEvaluacionId() {
        return this.modeloEvaluacionId;
    }
    
    public void setModeloEvaluacionId(int modeloEvaluacionId) {
        this.modeloEvaluacionId = modeloEvaluacionId;
    }
    public int getModeloCompetenciaId() {
        return this.modeloCompetenciaId;
    }
    
    public void setModeloCompetenciaId(int modeloCompetenciaId) {
        this.modeloCompetenciaId = modeloCompetenciaId;
    }
    public byte getEsExacto() {
        return this.esExacto;
    }
    
    public void setEsExacto(byte esExacto) {
        this.esExacto = esExacto;
    }


   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof ModeloEvaluacionXCompetenciaId) ) return false;
		 ModeloEvaluacionXCompetenciaId castOther = ( ModeloEvaluacionXCompetenciaId ) other; 
         
		 return (this.getModeloEvaluacionId()==castOther.getModeloEvaluacionId())
 && (this.getModeloCompetenciaId()==castOther.getModeloCompetenciaId())
 && (this.getEsExacto()==castOther.getEsExacto());
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + this.getModeloEvaluacionId();
         result = 37 * result + this.getModeloCompetenciaId();
         result = 37 * result + this.getEsExacto();
         return result;
   }   


}


