
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ListadosDeAdmitidosPublicadosIT {

    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        driver = new ChromeDriver();
        baseUrl = "https://www.katalon.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void listarPorProgramas() throws Exception {
        driver.get("http://localhost:8080/SistemaAdmisiones/faces/Vistas/vistaInicio.xhtml");
        driver.findElement(By.xpath("//div[@id='j_idt22:j_idt23']/ul/li[10]/a/span")).click();
        WebElement recuperados = driver.findElement(By.id("formMostrarPO:recuperados"));
        String texto = recuperados.getText();
        Assert.assertEquals(texto, "Mostrando 7 de 7 Programas Ofertados");
    }

    @Test
    public void listarAdmitidosPrograma() throws Exception {
        driver.get("http://localhost:8080/SistemaAdmisiones/faces/Vistas/ListadosDeAdmitidos/listarAdmitidosPublicado.xhtml");
        driver.findElement(By.xpath("//button[@id='formMostrarPO:tablaMostrarPO:0:j_idt47']/span[2]")).click();
        WebElement recuperados = driver.findElement(By.id("formMostrarAspirantesPOPrograma:recuperados"));
        String texto = recuperados.getText();
        Assert.assertEquals(texto, "Mostrando 29 aspirantes en el listado");
    }

    @Test
    public void buscarProgramaLANo() throws Exception {
        driver.get("http://localhost:8080/SistemaAdmisiones/faces/Vistas/ListadosDeAdmitidos/listarAdmitidosPublicado.xhtml");
        driver.findElement(By.id("formMostrarPO:tablaMostrarPO:j_idt42:filter")).click();
        driver.findElement(By.id("formMostrarPO:tablaMostrarPO:j_idt42:filter")).clear();
        driver.findElement(By.id("formMostrarPO:tablaMostrarPO:j_idt42:filter")).sendKeys("xiuss");
        WebElement datatable = driver.findElement(By.id("formMostrarPO:tablaMostrarPO_data"));
        String texto = datatable.getText();
        Assert.assertEquals(texto, "No hay Programas Ofertados");
        
    }
    
    @Test
    public void buscarProgramaLASi() throws Exception {
        driver.get("http://localhost:8080/SistemaAdmisiones/faces/Vistas/ListadosDeAdmitidos/listarAdmitidosPublicado.xhtml");
        driver.findElement(By.id("formMostrarPO:tablaMostrarPO:j_idt42:filter")).click();
        driver.findElement(By.id("formMostrarPO:tablaMostrarPO:j_idt42:filter")).clear();
        driver.findElement(By.id("formMostrarPO:tablaMostrarPO:j_idt42:filter")).sendKeys("Artes");
        WebElement datatable = driver.findElement(By.id("formMostrarPO:tablaMostrarPO_data"));
        String texto = datatable.getText();
        if(!texto.contains("Artes")) {
            fail("No funciona el buscar");
        }        
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
