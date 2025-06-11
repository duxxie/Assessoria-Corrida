package assessoria.app;

import assessoria.controller.TreinoController;
import assessoria.model.dao.TreinoDAO;
import assessoria.service.TreinoService;
import assessoria.view.MensagemView;
import assessoria.view.TreinoView;

public class TreinoApp {

    private final TreinoView treinoView;
    private final TreinoController treinoController;
    private final TreinoService treinoService;
    private final TreinoDAO treinoDAO;
    private final MensagemView mensagemView;

    public TreinoApp() {
        this.mensagemView = new MensagemView();
        this.treinoDAO = new TreinoDAO();
        this.treinoService = new TreinoService(treinoDAO);
        this.treinoController = new TreinoController(treinoService);
        this.treinoView = new TreinoView(treinoController);
    }


}
