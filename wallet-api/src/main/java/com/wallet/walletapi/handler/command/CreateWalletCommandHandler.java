package com.wallet.walletapi.handler.command;

import com.wallet.common.mediator.CommandHandler;
import com.wallet.walletapi.contract.request.command.CreateWalletCommand;
import com.wallet.walletapi.contract.response.command.CreateWalletCommandResult;
import org.springframework.stereotype.Component;

@Component
public class CreateWalletCommandHandler implements CommandHandler<CreateWalletCommand, CreateWalletCommandResult> {

    @Override
    public CreateWalletCommandResult handle(CreateWalletCommand command) {
        // Burada iş mantığı (ör: DB kaydı, dummy response vs.)
        CreateWalletCommandResult result = new CreateWalletCommandResult();
        result.setWalletId("dummy-id");
        result.setUserId(command.getUserId());
        result.setCurrency(command.getCurrency());
        result.setBalance(0.0);
        return result;
    }
}
