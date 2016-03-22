package com.ericsson.appiot.demo.gateway.console;




public class RegistrationTicket {

    private String DataCollectorId;

    private String SecurityToken;

	private String SecurityTokenUpdate;

	private AccessTicket InboxAccessTicket;

	private AccessTicket OutboxAccessTicket;

	public String getDataCollectorId() {
		return DataCollectorId;
	}

	public void setDataCollectorId(String dataCollectorId) {
		this.DataCollectorId = dataCollectorId;
	}

	public String getSecurityToken() {
		return SecurityToken;
	}

	public void setSecurityToken(String securityToken) {
		this.SecurityToken = securityToken;
	}

	public String getSecurityTokenUpdate() {
		return SecurityTokenUpdate;
	}

	public void setSecurityTokenUpdate(String securityTokenUpdate) {
		this.SecurityTokenUpdate = securityTokenUpdate;
	}

	public AccessTicket getInboxAccessTicket() {
		return InboxAccessTicket;
	}

	public void setInboxAccessTicket(AccessTicket inboxAccessTicket) {
		this.InboxAccessTicket = inboxAccessTicket;
	}

	public AccessTicket getOutboxAccessTicket() {
		return OutboxAccessTicket;
	}

	public void setOutboxAccessTicket(AccessTicket outboxAccessTicket) {
		this.OutboxAccessTicket = outboxAccessTicket;
	}
}
