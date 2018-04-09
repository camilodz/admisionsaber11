package Controladores;

import Beans.ProgramacomponentesFacade;
import Modelo.Programacomponentes;
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

@Named("programacomponentesController")
@SessionScoped
public class ProgramacomponentesController implements Serializable {

    private Programacomponentes current;
    private DataModel items = null;
    @EJB
    private Beans.ProgramacomponentesFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ProgramacomponentesController() {
    }

    public Programacomponentes getSelected() {
        if (current == null) {
            current = new Programacomponentes();
            current.setProgramacomponentesPK(new Modelo.ProgramacomponentesPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProgramacomponentesFacade getFacade() {
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
        current = (Programacomponentes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Programacomponentes();
        current.setProgramacomponentesPK(new Modelo.ProgramacomponentesPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getProgramacomponentesPK().setCompid(current.getComponentesicfes().getCompid());
            current.getProgramacomponentesPK().setProid(current.getPrograma().getProid());
            current.getProgramacomponentesPK().setPerid(current.getPeriodoacademico().getPerid());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProgramacomponentesCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Programacomponentes) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getProgramacomponentesPK().setCompid(current.getComponentesicfes().getCompid());
            current.getProgramacomponentesPK().setProid(current.getPrograma().getProid());
            current.getProgramacomponentesPK().setPerid(current.getPeriodoacademico().getPerid());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProgramacomponentesUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Programacomponentes) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProgramacomponentesDeleted"));
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

    public Programacomponentes getProgramacomponentes(Modelo.ProgramacomponentesPK id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Programacomponentes.class)
    public static class ProgramacomponentesControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProgramacomponentesController controller = (ProgramacomponentesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "programacomponentesController");
            return controller.getProgramacomponentes(getKey(value));
        }

        Modelo.ProgramacomponentesPK getKey(String value) {
            Modelo.ProgramacomponentesPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new Modelo.ProgramacomponentesPK();
            key.setProid(Short.parseShort(values[0]));
            key.setPerid(new java.math.BigDecimal(values[1]));
            key.setCompid(Short.parseShort(values[2]));
            return key;
        }

        String getStringKey(Modelo.ProgramacomponentesPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getProid());
            sb.append(SEPARATOR);
            sb.append(value.getPerid());
            sb.append(SEPARATOR);
            sb.append(value.getCompid());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Programacomponentes) {
                Programacomponentes o = (Programacomponentes) object;
                return getStringKey(o.getProgramacomponentesPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Programacomponentes.class.getName());
            }
        }

    }

}
