package com.ericsson.appiot.demo.gateway.console;



public class AccessTicket {

    private String IssuedDateTime;
    private String ExpireDateTime;
    private String Namespace;
    private String IssuerName;
    private String HttpServiceUri;
    private String HttpServicePath;
    private String HttpSas;
    private String AmqpServiceUri;
    private String AmqpServicePath;
    private String AmqpSas;
    
	public String getIssuedDateTime() {
		return IssuedDateTime;
	}
	public void setIssuedDateTime(String issuedDateTime) {
		this.IssuedDateTime = issuedDateTime;
	}
	public String getExpireDateTime() {
		return ExpireDateTime;
	}
	public void setExpireDateTime(String expireDateTime) {
		this.ExpireDateTime = expireDateTime;
	}
	public String getNamespace() {
		return Namespace;
	}
	public void setNamespace(String namespace) {
		this.Namespace = namespace;
	}
	public String getIssuerName() {
		return IssuerName;
	}
	public void setIssuerName(String issuerName) {
		this.IssuerName = issuerName;
	}
	public String getHttpServiceUri() {
		return HttpServiceUri;
	}
	public void setHttpServiceUri(String httpServiceUri) {
		this.HttpServiceUri = httpServiceUri;
	}
	public String getHttpServicePath() {
		return HttpServicePath;
	}
	public void setHttpServicePath(String httpServicePath) {
		this.HttpServicePath = httpServicePath;
	}
	public String getHttpSas() {
		return HttpSas;
	}
	public void setHttpSas(String httpSas) {
		this.HttpSas = httpSas;
	}
	public String getAmqpServiceUri() {
		return AmqpServiceUri;
	}
	public void setAmqpServiceUri(String amqpServiceUri) {
		this.AmqpServiceUri = amqpServiceUri;
	}
	public String getAmqpServicePath() {
		return AmqpServicePath;
	}
	public void setAmqpServicePath(String amqpServicePath) {
		this.AmqpServicePath = amqpServicePath;
	}
	public String getAmqpSas() {
		return AmqpSas;
	}
	public void setAmqpSas(String amqpSas) {
		this.AmqpSas = amqpSas;
	}
}
