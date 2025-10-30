package org.lorem.profilesservice.interfaces.acl;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileContextFacade {

    private final ClientCommandService clientCommandService;
    private final LawyerCommandService lawyerCommandService;
    private final LawyerQueryService lawyerQueryService;
    private final ClientQueryService clientQueryService;

    public ProfileContextFacade(ClientCommandService clientCommandService, LawyerCommandService lawyerCommandService, LawyerQueryService lawyerQueryService, ClientQueryService clientQueryService) {
        this.clientCommandService = clientCommandService;
        this.lawyerCommandService = lawyerCommandService;
        this.lawyerQueryService = lawyerQueryService;
        this.clientQueryService = clientQueryService;
    }


    public Optional<Lawyer> getLawyerById(Long lawyerId){
        return lawyerQueryService.handle(new GetLawyerByIdQuery(lawyerId));
    }

    public Optional<Client> getClientById(Long clientId){
        return clientQueryService.handle(new GetClientByIdQuery(clientId));
    }

    public void createClient(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String address,
            String dni,
            String image_url
    )
            {
        clientCommandService.handle(new CreateClientCommand(
                firstName,
                lastName,
                email,
                phoneNumber,
                address,
                dni,
                image_url
        ));
    }

    public void createLawyer(
            String firstName,
            String lastName,
            String email,
            String phoneNumber,
            String address,
            String dni,
            String image_url
    )
            {
        lawyerCommandService.handle(new CreateLawyerCommand(
                firstName,
                lastName,
                email,
                phoneNumber,
                address,
                dni,
                image_url
        ));
    }
}
