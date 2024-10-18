package com.sherpa.interviewsherpa.member.adapter.out.persistence;

import java.util.Optional;

import com.sherpa.interviewsherpa.common.annotation.PersistenceAdapter;
import com.sherpa.interviewsherpa.member.adapter.out.persistence.repository.MemberRepository;
import com.sherpa.interviewsherpa.member.application.port.out.LoadOAuthMemberPort;
import com.sherpa.interviewsherpa.member.application.port.out.SaveOAuthMemberPort;
import com.sherpa.interviewsherpa.member.domain.Member;
import com.sherpa.interviewsherpa.member.exception.MemberNotFoundException;

@PersistenceAdapter
public class MemberPersistenceAdapter implements LoadOAuthMemberPort, SaveOAuthMemberPort {

	private final MemberRepository memberRepository;
	private final MemberMapper memberMapper;

	public MemberPersistenceAdapter(MemberRepository memberRepository, MemberMapper memberMapper) {
		this.memberRepository = memberRepository;
		this.memberMapper = memberMapper;
	}

	@Override
	public Member loadOAuthMember(String email) {
		return memberRepository.findByEmail(email)
			.map(memberMapper::mapToDomainEntity)
			.orElseThrow(() -> new MemberNotFoundException(email));
	}

	@Override
	public Optional<Member> loadOAuthMemberOrNull(String email) {
		return memberRepository.findByEmail(email)
			.map(memberMapper::mapToDomainEntity);
	}

	@Override
	public Member saveOAuthMember(Member member) {
		return memberMapper.mapToDomainEntity(memberRepository.save(memberMapper.mapToJpaEntity(member)));
	}
}
