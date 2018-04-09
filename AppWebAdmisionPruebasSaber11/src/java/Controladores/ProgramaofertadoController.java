package Controladores;

import Beans.ProgramaofertadoFacade;
import Modelo.Programaofertado;
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

@Named("programaofertadoController")
@SessionScoped
public class ProgramaofertadoController implements Serializable {

    private Programaofertado current;
    private DataModel items = null;
    @EJB
    private Beans.ProgramaofertadoFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ProgramaofertadoController() {
    }

    public Programaofertado getSelected() {
        if (current == null) {
            current = new Programaofertado();
            current.setProgramaofertadoPK(new Modelo.ProgramaofertadoPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProgramaofertadoFacade getFacade() {
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
        current = (Programaofertado) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Programaofertado();
        current.setProgramaofertadoPK(new Modelo.ProgramaofertadoPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getProgramaofertadoPK().setProid(current.getPrograma().getProid());
            current.getProgramaofertadoPK().setPerid(current.getPeriodoacademico().getPerid());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProgramaofertadoCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Programaofertado) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getProgramaofertadoPK().setProid(current.getPrograma().getProid());
            current.getProgramaofertadoPK().setPerid(current.getPeriodoacademico().getPerid());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProgramaofertadoUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Programaofertado) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProgramaofertadoDeleted"));
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

    public Programaofertado getProgramaofertado(Modelo.ProgramaofertadoPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Programaofertado.class)
    public static class ProgramaofertadoControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProgramaofertadoController controller = (ProgramaofertadoController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "programaofertadoController");
            return controller.getProgramaofertado(getKey(value));
        }

        Modelo.ProgramaofertadoPK getKey(String value) {
            Modelo.ProgramaofertadoPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Modelo.ProgramaofertadoPK();
            key.setProid(Short.parseShort(values[0]));
            key.setPerid(new java.math.BigDecimal(values[1]));
            return key;
        }

        String getStringKey(Modelo.ProgramaofertadoPK value) {
            StringBuilder sb = new StringBuilder();
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
            if (object instanceof Programaofertado) {
                Programaofertado o = (Programaofertado) object;
                return getStringKey(o.getProgramaofertadoPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Programaofertado.class.getName());
            }
        }

    }

}
