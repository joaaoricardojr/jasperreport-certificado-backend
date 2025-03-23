package tech.joaoricardojr.jasperreport.service;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import tech.joaoricardojr.jasperreport.model.Aluno;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperReportService {

    public static final String CERTIFICADOS = "classpath:jasper/certificados";
    public static final String ARQUIVOJRXML = "cert.jrxml";
    public static final Logger LOGGER = LoggerFactory.getLogger(JasperReportService.class);

    public void gerar(Aluno aluno) throws FileNotFoundException {

        Map<String, Object> params = new HashMap<>();
        params.put("nome", aluno.getNome());
        params.put("curso", aluno.getCurso());
        params.put("cargaHoraria", aluno.getCargaHoraria());
        params.put("dataInicioCurso", aluno.getDataInicioCurso());
        params.put("dataTerminoCurso", aluno.getDataTerminoCurso());

        String pathAbsoluto = getAbsolutePath();

        try{
            JasperReport report = JasperCompileManager.compileReport(pathAbsoluto);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }

    private String getAbsolutePath() throws FileNotFoundException {
        return ResourceUtils.getFile(CERTIFICADOS+ARQUIVOJRXML).getAbsolutePath();
    }
}
