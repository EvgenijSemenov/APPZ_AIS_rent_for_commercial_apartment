package service;

import dao.LegalPersonDao;
import dao.NaturalPersonDao;

import java.util.ArrayList;
import java.util.List;

public class RenterService {

    private LegalPersonDao legalPersonDao = new LegalPersonDao();
    private NaturalPersonDao naturalPersonDao = new NaturalPersonDao();

    public List allByOrganizationId(int id) {
        List renters = new ArrayList();
        renters.addAll(legalPersonDao.allByOrganizationId(id));
        renters.addAll(naturalPersonDao.allByOrganizationId(id));

        return renters;
    }

}
