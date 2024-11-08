package com.sherpa.interviewsherpa.auth.application.port.in;

public interface OAuthSignInMemberUseCase {

	OAuthSignInMemberResult signInOAuthMember(OAuthSignInMemberCommand command);

}
