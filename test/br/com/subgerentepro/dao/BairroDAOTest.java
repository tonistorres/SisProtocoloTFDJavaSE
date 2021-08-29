package br.com.subgerentepro.dao;

import br.com.subgerentepro.dto.BairroDTO;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
/**
 *
 * @author Dã Torres
 */
public class BairroDAOTest {

    private BairroDAO bairroDao;
    private BairroDTO bairroDto;

    public BairroDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        bairroDao = new BairroDAO();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of inserir method, of class BairroDAO.
     */
    @Test
    public void testInserir() throws Exception {

        try {

            bairroDto.setNomeBairroDto("CENTRO");
            bairroDto.setChaveEstrangeiraIdCidadeDto(457);
            bairroDao.inserir(bairroDto);

        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }

    }

    /**
     * Test of atualizar method, of class BairroDAO.
     */
    @Test
    @Ignore
    public void testAtualizar() throws Exception {

    }

    /**
     * Test of deletar method, of class BairroDAO.
     */
    @Test
    @Ignore
    public void testDeletar() throws Exception {

    }

    /**
     * Test of listarTodos method, of class BairroDAO.
     */
    @Test
    @Ignore
    public void testListarTodos() throws Exception {

    }

    /**
     * Test of buscarPorId method, of class BairroDAO.
     */
    @Test
    @Ignore
    public void testBuscarPorId() throws Exception {

    }

    /**
     * Test of buscarPor method, of class BairroDAO.
     */
    @Test
    @Ignore
    public void testBuscarPor() throws Exception {

    }

    /**
     * Test of verificaDuplicidade method, of class BairroDAO.
     */
    @Test
    @Ignore
    public void testVerificaDuplicidade() throws Exception {

    }

    /**
     * Test of filtrarAoClicar method, of class BairroDAO.
     */
    @Test
    @Ignore
    public void testFiltrarAoClicar() throws Exception {

    }

    /**
     * Test of listarComboCidades method, of class BairroDAO.
     */
    @Test
    @Ignore
    public void testListarComboCidades() throws Exception {

    }

    /**
     * Test of filtrarBairrosPesqRapidaComFiltroCidade method, of class
     * BairroDAO.
     */
    @Test
    @Ignore
    public void testFiltrarBairrosPesqRapidaComFiltroCidade() throws Exception {

    }

    /**
     * Test of filtrarBairrosPesqRapida method, of class BairroDAO.
     */
    @Test
    @Ignore
    public void testFiltrarBairrosPesqRapida() throws Exception {

    }

    /**
     * Test of deletarPorCodigoTabela method, of class BairroDAO.
     */
    @Test
    @Ignore
    public void testDeletarPorCodigoTabela() throws Exception {

    }

    /**
     * Test of deletarTudo method, of class BairroDAO.
     */
    @Test
    @Ignore
    public void testDeletarTudo() throws Exception {

    }

}
