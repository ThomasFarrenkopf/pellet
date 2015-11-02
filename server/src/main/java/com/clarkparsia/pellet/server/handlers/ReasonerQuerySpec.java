package com.clarkparsia.pellet.server.handlers;

import com.clarkparsia.pellet.server.model.ServerState;
import com.complexible.pellet.service.messages.GenericJsonMessage;
import com.google.inject.Inject;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.util.Headers;

/**
 * @author Edgar Rodriguez-Diaz
 */
public class ReasonerQuerySpec extends ReasonerSpec {

	@Inject
	public ReasonerQuerySpec(final ServerState theServerState) {
		super(theServerState);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getPath() {
		return path("query");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public HttpHandler getHandler() {
		return new ReasonerQueryHandler(mServerState);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isExactPath() {
		return true;
	}

	static class ReasonerQueryHandler implements HttpHandler {

		private final ServerState serverState;

		public ReasonerQueryHandler(final ServerState theServerState) {
			serverState = theServerState;
		}

		@Override
		public void handleRequest(final HttpServerExchange theHttpServerExchange) throws Exception {
			GenericJsonMessage aMessage = new GenericJsonMessage("Doing reasoning query hmm...");

			theHttpServerExchange.getResponseHeaders().put(Headers.CONTENT_TYPE, aMessage.getMimeType());
			theHttpServerExchange.getResponseSender().send(aMessage.toJsonString());
			// TODO: implement using a ClientState -> schema reasoner
		}
	}
}