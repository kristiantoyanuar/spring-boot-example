package com.kristiantoyanuar.api.client.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import com.kristiantoyanuar.model.AbstractEntity;

/**
 * Created by Kristianto Yanuar on 5/31/2016.
 */
@Entity
@Table(name = "api_client")
public class ApiClient extends AbstractEntity<String> implements ClientDetails {

	public static final List<GrantedAuthority> DEFAULT_CLIENT_AUTHORITIES = Collections
			.unmodifiableList(Arrays.asList(new SimpleGrantedAuthority("ROLE_CLIENT")));

	@Id
	@Column(name = "client_id")
	@GeneratedValue(generator = "client_id_generator")
	@GenericGenerator(name = "client_id_generator", strategy = "uuid2")
	private String clientId;

	@Column(name = "client_name")
	private String clientName;

	@ElementCollection
	@CollectionTable(name = "api_client_resources", joinColumns = @JoinColumn(name = "client_id"))
	@Column(name = "resource")
	private Set<String> resourceIds;

	@Column(name = "is_client_secret_required")
	private Boolean clientSecretRequired;

	@Column(name = "client_secret")
	private String clientSecret;

	@Column(name = "is_scoped")
	private Boolean scoped;

	@ElementCollection
	@CollectionTable(name = "api_client_scope", joinColumns = @JoinColumn(name = "client_id"))
	@Column(name = "scope")
	private Set<String> scope;

	@ElementCollection
	@CollectionTable(name = "api_client_grant_type", joinColumns = @JoinColumn(name = "client_id"))
	@Column(name = "authorized_grant_type")
	private Set<String> authorizedGrantTypes;

	@ElementCollection
	@CollectionTable(name = "api_client_redirect_uri", joinColumns = @JoinColumn(name = "client_id"))
	@Column(name = "redirect_uri")
	private Set<String> registeredRedirectUri;

	// SET & GET
	@Override
	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	@Override
	public Set<String> getResourceIds() {
		return resourceIds;
	}

	@Override
	public boolean isSecretRequired() {
		return false;
	}

	public void setResourceIds(Set<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	public Boolean getClientSecretRequired() {
		return clientSecretRequired;
	}

	public void setClientSecretRequired(Boolean clientSecretRequired) {
		this.clientSecretRequired = clientSecretRequired;
	}

	@Override
	public String getClientSecret() {
		return clientSecret;
	}

	@Override
	public boolean isScoped() {
		return false;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public Boolean getScoped() {
		return scoped;
	}

	public void setScoped(Boolean scoped) {
		this.scoped = scoped;
	}

	@Override
	public Set<String> getScope() {
		return scope;
	}

	public void setScope(Set<String> scope) {
		this.scope = scope;
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return authorizedGrantTypes;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return DEFAULT_CLIENT_AUTHORITIES;
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return 3600;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return 3600;
	}

	@Override
	public boolean isAutoApprove(String s) {
		return false;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		return Collections.EMPTY_MAP;
	}

	public void setAuthorizedGrantTypes(Set<String> authorizedGrantTypes) {
		this.authorizedGrantTypes = authorizedGrantTypes;
	}

	public Set<String> getRegisteredRedirectUri() {
		return registeredRedirectUri;
	}

	public void setRegisteredRedirectUri(Set<String> registeredRedirectUri) {
		this.registeredRedirectUri = registeredRedirectUri;
	}

	@Override
	public String getId() {
		return getClientId();
	}

	@Override
	public void setId(String s) {
		setClientId(s);
	}

}
