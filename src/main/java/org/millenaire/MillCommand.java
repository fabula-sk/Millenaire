package org.millenaire;

import java.util.Map.Entry;

import org.millenaire.blocks.MillBlocks;
import org.millenaire.blocks.StoredPosition;
import org.millenaire.building.BuildingTypes;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.network.chat.Component;

/**
 * Brigadier implementation of the old Mill command.
 */
public class MillCommand {

    private static final String[] OPTIONS = {"village", "loneBuildings", "showBuildPoints"};

    /**
     * Register this command to the dispatcher.
     */
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("mill")
            .then(Commands.argument("action", StringArgumentType.word())
                .suggests((context, builder) -> SharedSuggestionProvider.suggest(OPTIONS, builder))
                .executes(MillCommand::execute)));
    }

    private static int execute(CommandContext<CommandSourceStack> context) throws CommandSyntaxException {
        String action = StringArgumentType.getString(context, "action");
        CommandSourceStack source = context.getSource();

        switch (action) {
            case "village":
                for (Entry<?, ?> ent : BuildingTypes.getCache().entrySet()) {
                    source.sendSuccess(Component.literal(ent.getKey() + " - " + ent.getValue()), false);
                }
                break;
            case "loneBuildings":
                source.sendSuccess(Component.literal("loneBuildings not implemented"), false);
                break;
            case "showBuildPoints":
                StoredPosition posBlock = (StoredPosition) MillBlocks.storedPosition;
                posBlock.setShowParticles(!posBlock.getShowParticles());
                source.sendSuccess(Component.literal("Toggled build point particles"), true);
                break;
            default:
                source.sendFailure(Component.literal("Invalid argument"));
                break;
        }

        return 1;
    }
}
