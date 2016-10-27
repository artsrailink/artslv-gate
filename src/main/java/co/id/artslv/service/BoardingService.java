package co.id.artslv.service;

import co.id.artslv.lib.boarding.Boarding;
import co.id.artslv.lib.responses.MessageWrapper;
import co.id.artslv.lib.utility.CustomErrorResponse;
import co.id.artslv.lib.utility.CustomException;
import co.id.artslv.repository.BoardingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by root on 26/09/16.
 */
@Service
public class BoardingService {
    @Autowired
    private BoardingRepository boardingRepository;

    @Transactional(rollbackFor = CustomException.class)
    public MessageWrapper insertGateIn(Boarding boarding) throws CustomException {
        if (boarding != null) {
            if ((boarding.getBookcode() == null || boarding.getBookcode().trim().equals("")) &&
                    (boarding.getTicketnum() == null || boarding.getTicketnum().trim().equals("")) &&
                    (boarding.getStasiunid() == null || boarding.getStasiunid().trim().equals("")) &&
                    (boarding.getStasiuncode() == null || boarding.getStasiuncode().trim().equals("")) &&
                    (boarding.getUnitid() == null || boarding.getUnitid().trim().equals("")) &&
                    (boarding.getUnitcode() == null || boarding.getUnitcode().trim().equals("")) &&
                    (boarding.getUnitcode() == null)
                    ) {
                throw new CustomException(new CustomErrorResponse("02", "Bookcode, Ticketnum, Stasiun Id, Stasiun Code, Unit Id, Unit Code And Trip Date must be filled"));
            } else {
                if (boarding.getBookcode().length() != 7 && boarding.getTicketnum().length() != 10) {
                    throw new CustomException(new CustomErrorResponse("04", "Transaction not found"));
                } else {
                    List<Boarding> datapay = boardingRepository.findByTicketnum(boarding.getTicketnum());
                    if (datapay != null) {
                        if (!datapay.isEmpty()) {
                            if (datapay.stream().anyMatch(p->p.getStatus().equals("2"))) {
                                if (!datapay.stream().anyMatch(p->p.getStatus().equals("3") || p.getStatus().equals("4"))) {
                                        boarding.setStatus("3");
                                        boarding.setDomain(datapay.get(0).getDomain());
                                        boarding.setCreatedon(LocalDateTime.now());
                                        boarding.setCreatedby("userpos");
                                        boarding.setModifiedon(LocalDateTime.now());
                                        boarding.setModifiedby("userpos");
                                        boardingRepository.save(boarding);
                                        MessageWrapper resultWrapperPayment = new MessageWrapper<>("00", "GATE IN SUCCESS");
                                        return resultWrapperPayment;
                                }
                                else{
                                    throw new CustomException(new CustomErrorResponse("03", "Already Boarding"));
                                }                            
                            }    
                            else{
                                    throw new CustomException(new CustomErrorResponse("04", "Transaction not found"));
                                }
                        } else {
                            throw new CustomException(new CustomErrorResponse("04", "Transaction not found"));
                        }
                    } else {
                        throw new CustomException(new CustomErrorResponse("04", "Transaction not found"));
                    }
                }
            }

        }else{
            throw new CustomException(new CustomErrorResponse("02", "Bookcode, Ticketnum, Stasiun Id, Stasiun Code, Unit Id, Unit Code And Trip Date must be filled"));
        }
    }
    
    @Transactional(rollbackFor = CustomException.class)
    public MessageWrapper insertGateOut(Boarding boarding) throws CustomException {
        if (boarding != null) {
            if ((boarding.getBookcode() == null || boarding.getBookcode().trim().equals("")) &&
                    (boarding.getTicketnum() == null || boarding.getTicketnum().trim().equals("")) &&
                    (boarding.getStasiunid() == null || boarding.getStasiunid().trim().equals("")) &&
                    (boarding.getStasiuncode() == null || boarding.getStasiuncode().trim().equals("")) &&
                    (boarding.getUnitid() == null || boarding.getUnitid().trim().equals("")) &&
                    (boarding.getUnitcode() == null || boarding.getUnitcode().trim().equals("")) &&
                    (boarding.getUnitcode() == null)
                    ) {
                throw new CustomException(new CustomErrorResponse("02", "Bookcode, Ticketnum, Stasiun Id, Stasiun Code, Unit Id, Unit Code And Trip Date must be filled"));
            } else {
                if (boarding.getBookcode().length() != 7 && boarding.getTicketnum().length() != 10) {
                    throw new CustomException(new CustomErrorResponse("04", "Transaction not found"));
                } else {
                    List<Boarding> datapay = boardingRepository.findByTicketnum(boarding.getTicketnum());
                    if (datapay != null) {
                        if (!datapay.isEmpty()) {
                            if (datapay.stream().anyMatch(p->p.getStatus().equals("3"))) {
                                if (!datapay.stream().anyMatch(p->p.getStatus().equals("4"))) {
                                    boarding.setStatus("4");
                                    boarding.setDomain(datapay.get(0).getDomain());
                                    boarding.setCreatedon(LocalDateTime.now());
                                    boarding.setCreatedby("userpos");
                                    boarding.setModifiedon(LocalDateTime.now());
                                    boarding.setModifiedby("userpos");
                                    boardingRepository.save(boarding);
                                    MessageWrapper resultWrapperPayment = new MessageWrapper<>("00", "GATE OUT SUCCESS");
                                    return resultWrapperPayment;
                                }
                                else{
                                    throw new CustomException(new CustomErrorResponse("03", "Already Boarding Out"));
                                }                            
                            }    
                            else{
                                    throw new CustomException(new CustomErrorResponse("04", "Transaction not found"));
                                }
                        } else {
                            throw new CustomException(new CustomErrorResponse("04", "Transaction not found"));
                        }
                    } else {
                        throw new CustomException(new CustomErrorResponse("04", "Transaction not found"));
                    }
                }
            }

        }else{
            throw new CustomException(new CustomErrorResponse("02", "Bookcode, Ticketnum, Stasiun Id, Stasiun Code, Unit Id, Unit Code And Trip Date must be filled"));
        }
    }

}
