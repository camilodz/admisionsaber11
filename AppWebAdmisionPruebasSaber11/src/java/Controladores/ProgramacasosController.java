package Controladores;

import Beans.ProgramacasosFacade;
import Modelo.Programacasos;
import Beans.util.JsfUtil;
import Beans.util.PaginationHelper;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("programacasosController")
@SessionScoped
public class ProgramacasosController implements Serializable {

    private Programacasos current;
    private DataModel items = null;
    @EJB
    private Beans.ProgramacasosFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ProgramacasosController() {
    }

    public Programacasos getSelected() {
        if (current == null) {
            current = new Programacasos();
            current.setProgramacasosPK(new Modelo.ProgramacasosPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProgramacasosFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (Programacasos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Programacasos();
        current.setProgramacasosPK(new Modelo.ProgramacasosPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getProgramacasosPK().setProid(current.getPrograma().getProid());
            current.getProgramacasosPK().setEspid(current.getCasosespeciales().getEspid());
            current.getProgramacasosPK().setPerid(current.getPeriodoacademico().getPerid());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProgramacasosCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Programacasos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getProgramacasosPK().setProid(current.getPrograma().getProid());
            current.getProgramacasosPK().setEspid(current.getCasosespeciales().getEspid());
            current.getProgramacasosPK().setPerid(current.getPeriodoacademico().getPerid());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProgramacasosUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Programacasos) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProgramacasosDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Programacasos getProgramacasos(Modelo.ProgramacasosPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Programacasos.class)
    public static class ProgramacasosControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProgramacasosController controller = (ProgramacasosController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "programacasosController");
            return controller.getProgramacasos(getKey(value));
        }

        Modelo.ProgramacasosPK getKey(String value) {
            Modelo.ProgramacasosPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Modelo.ProgramacasosPK();
            key.setEspid(Short.parseShort(values[0]));
            key.setProid(Short.parseShort(values[1]));
            key.setPerid(new java.math.BigDecimal(values[2]));
            return key;
        }

        String getStringKey(Modelo.ProgramacasosPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getEspid());
            sb.append(SEPARATOR);
            sb.append(value.getProid());
            sb.append(SEPARATOR);
            sb.append(value.getPerid());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Programacasos) {
                Programacasos o = (Programacasos) object;
                return getStringKey(o.getProgramacasosPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Programacasos.class.getName());
            }
        }

    }

}
