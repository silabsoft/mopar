package io.mopar.game.lua;

import io.mopar.game.action.ActionBindings;
import io.mopar.game.action.TargetType;
import io.mopar.core.lua.Coerce;
import io.mopar.core.lua.LuaModule;
import org.luaj.vm2.*;

/**
 * @author Hadyn Fitzgerald
 */
public class ActionsLuaModule implements LuaModule {

    /**
     * The action bindings.
     */
    private ActionBindings bindings;

    /**
     * Constructs a new {@link ActionsLuaModule};
     *
     * @param bindings The action bindings.
     */
    public ActionsLuaModule(ActionBindings bindings) {
        this.bindings = bindings;
    }

    /**
     *
     * @param inter
     * @param option
     * @param closure
     */
    public void on_inter_item_option(LuaTable inter, int option, LuaClosure closure) {
        bindings.registerInterfaceItemMenuAction((player, itemId, slot) -> closure.invoke(new LuaValue[]{
                Coerce.coerceToLua(player),
                Coerce.coerceToLua(itemId),
                Coerce.coerceToLua(slot)
        }), inter.get("parent_id").checkint(), inter.get("id").checkint(), option);
    }

    /**
     *
     * @param inter
     * @param item
     * @param opt
     * @param closure
     */
    public void on_item_option(LuaTable inter, LuaTable item, int opt, LuaClosure closure) {
        bindings.registerItemMenuAction((player, itemId, slot) -> closure.invoke(new LuaValue[]{
                Coerce.coerceToLua(player), Coerce.coerceToLua(itemId), Coerce.coerceToLua(slot)
        }), inter.get("parent_id").checkint(), inter.get("id").checkint(), item.get("id").checkint(), opt);
    }

    /**
     *
     * @param name
     * @param closure
     */
    public void on_command(String name, LuaClosure closure) {
        bindings.registerCommandAction((plr, cmd, args) -> closure.call(Coerce.coerceToLua(plr),
                Coerce.coerceToLua(cmd), Coerce.coerceToLua(args)), name);
    }

    /**
     *
     * @param inter
     * @param closure
     */
    public void on_switch_item(LuaTable inter, LuaClosure closure) {
        on_switch_item(inter.get("parent_id").checkint(), inter.get("id").checkint(), closure);
    }

    /**
     *
     * @param widgetId
     * @param componentId
     * @param function
     */
    public void on_switch_item(int widgetId, int componentId, LuaClosure function) {
        bindings.registerSwapItemAction((plr, first, second, mode) ->
                function.invoke(new LuaValue[]{
                        Coerce.coerceToLua(plr),
                        Coerce.coerceToLua(first),
                        Coerce.coerceToLua(second),
                        Coerce.coerceToLua(mode)
                }), widgetId, componentId);
    }

    /**
     *
     * @param table
     */
    public void on_button(LuaTable table, int option, LuaFunction closure) {
        on_button(table.get("parent_id").checkint(), table.get("id").checkint(), option, closure);
    }

    /**
     *
     * @param widgetId
     * @param componentId
     * @param option
     * @param closure
     */
    public void on_button(int widgetId, int componentId, int option, LuaFunction closure) {
        bindings.registerButtonMenuAction(((plr, widget, comp, child, opt) ->
                closure.invoke(new LuaValue[]{
                        Coerce.coerceToLua(plr),
                        Coerce.coerceToLua(widgetId),
                        Coerce.coerceToLua(componentId),
                        Coerce.coerceToLua(child),
                        Coerce.coerceToLua(opt)
                })), widgetId, componentId, option);
    }

    /**
     * Binds a menu option action.
     *
     * @param target The target.
     * @param option The menu option.
     * @param closure The lua closure to wrap the action with.
     */
    public void on_option(String target, int option, LuaFunction closure) {
        on_option(target, ActionBindings.NO_TYPE, option, closure);
    }

    /**
     * Binds a menu option action.
     *
     * @param target The target.
     * @param typeId The target type id.
     * @param option The menu option.
     * @param closure The lua closure to wrap the action with.
     */
    public void on_option(String target, int typeId, int option, LuaFunction closure) {
        bindings.registerEntityMenuAction(TargetType.valueOf(target.toUpperCase()),
                (player, entity, opt) -> closure.invoke(
                        Coerce.coerceToLua(player),
                        Coerce.coerceToLua(entity),
                        LuaNumber.valueOf(opt)),
                typeId, option);
    }

    /**
     * Gets the module namespace.
     *
     * @return The namespace.
     */
    @Override
    public String getNamespace() {
        return "action";
    }
}
