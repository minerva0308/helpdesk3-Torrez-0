
package controlador;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.InterfazPrincipal;

public class LoginController implements MouseListener{
    private InterfazPrincipal views;
    public LoginController(InterfazPrincipal views){
        this.views = views;    
        
        this.views.jLabelIncidencias.addMouseListener(this);
        this.views.jLabelUsuarios.addMouseListener(this);
        this.views.jLabelTecnicos.addMouseListener(this);
        this.views.jLabelCategorias.addMouseListener(this);
        this.views.jLabelEspecialidades.addMouseListener(this);
        this.views.jLabelReportes.addMouseListener(this);
        this.views.jLabelAsistenteIA.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        
    }

    @Override
    public void mousePressed(MouseEvent me) {
       
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        if(me.getSource() == views.jLabelIncidencias){
            views.jPanelIncidencias.setBackground(new Color(0,102,204));
        }
        else if(me.getSource() == views.jLabelUsuarios){
            views.jPanelUsuarios.setBackground(new Color(0,102,204));  
        }
         else if(me.getSource() == views.jLabelTecnicos){
            views.jPanelTecnicos.setBackground(new Color(0,102,204));  
        }
         else if(me.getSource() == views.jLabelCategorias){
            views.jPanelCategorias.setBackground(new Color(0,102,204));  
        }
         else if(me.getSource() == views.jLabelEspecialidades){
            views.jPanelEspecialidades.setBackground(new Color(0,102,204));  
        }
         else if(me.getSource() == views.jLabelReportes){
            views.jPanelReportes.setBackground(new Color(0,102,204));  
        }
         else if(me.getSource() == views.jLabelAsistenteIA){
            views.jPanelAsistenteIA.setBackground(new Color(0,102,204));  
        }
         
        
    }

    @Override
    public void mouseExited(MouseEvent me) {
       if(me.getSource() == views.jLabelIncidencias){
            views.jPanelIncidencias.setBackground(new Color(0,0,102));
        }
        else if(me.getSource() == views.jLabelUsuarios){
            views.jPanelUsuarios.setBackground(new Color(0,0,102));  
        }
         else if(me.getSource() == views.jLabelTecnicos){
            views.jPanelTecnicos.setBackground(new Color(0,0,102));  
        }
         else if(me.getSource() == views.jLabelCategorias){
            views.jPanelCategorias.setBackground(new Color(0,0,102));  
        }
         else if(me.getSource() == views.jLabelEspecialidades){
            views.jPanelEspecialidades.setBackground(new Color(0,0,102));  
        }
         else if(me.getSource() == views.jLabelReportes){
            views.jPanelReportes.setBackground(new Color(0,0,102));  
        }
         else if(me.getSource() == views.jLabelAsistenteIA){
            views.jPanelAsistenteIA.setBackground(new Color(0,0,102));  
        }
    }
    
}
