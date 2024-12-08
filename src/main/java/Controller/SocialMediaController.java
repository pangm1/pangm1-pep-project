package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;
import Model.*;
import Service.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        // app.get("example-endpoint", this::exampleHandler);
        app.post("register", this::registerHandler);
        app.post("login", this::loginHandler);
        app.post("messages", this::postHandler);
        app.get("messages", this::getMessagesHandler);
        app.get("messages/{message_id}", this::getOneMessageHandler);
        app.delete("messages/{message_id}", this::deleteMessageHandler);
        app.patch("messages/{message_id}", this::updateMessageHandler);
        app.get("accounts/{account_id}/messages", this::accountMessagesHandler);

        return app;
    }

    // /**
    //  * This is an example handler for an example endpoint.
    //  * @param context The Javalin Context object manages information about both the HTTP request and response.
    //  */
    // private void exampleHandler(Context context) {
    //     // context.json("sample text");
    // }

    private void registerHandler(Context ctx) {
        Account registration = AccountService.add(ctx.body());
        if (registration == null) ctx.status(400);
        else ctx.json(registration);
    } 
    private void loginHandler(Context ctx) {
        Account account = AccountService.get(ctx.body());
        if (account == null) ctx.status(401);
        else ctx.json(account);
    } 
    private void postHandler(Context ctx) {
        Message post = MessageService.add(ctx.body());
        if (post == null) ctx.status(400);
        else ctx.json(post);
    } 
    private void getMessagesHandler(Context ctx) {
        ctx.json(MessageService.getAll());
    } 
    private void getOneMessageHandler(Context ctx) {
        Message message = MessageService.get(ctx.pathParam("message_id"));
        if (message != null) ctx.json(message);
        else ctx.status(200);
    } 
    private void deleteMessageHandler(Context ctx) {
        Message message = MessageService.delete(ctx.pathParam("message_id"));
        if (message != null) ctx.json(message);
        else ctx.status(200);
    } 
    private void updateMessageHandler(Context ctx) {
        Message updated = MessageService.update(ctx.pathParam("message_id"), ctx.body());
        if (updated == null) ctx.status(400);
        else ctx.json(updated);
    } 
    private void accountMessagesHandler(Context ctx) {
        ctx.json(MessageService.getFromAccountID(ctx.pathParam("account_id")));
    } 
}