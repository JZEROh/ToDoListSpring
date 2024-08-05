import com.smhrd.todolist.Model.Member;
import com.smhrd.todolist.Model.MembersDetails;
import com.smhrd.todolist.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtDecoder jwtDecoder;

    @Autowired
    private MemberRepository memberRepository; // MemberRepository 주입

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = extractTokenFromRequest(request); // Authorization 헤더에서 JWT 추출

        if (token != null && !token.isEmpty()) {
            try {
                Jwt jwt = jwtDecoder.decode(token); // JWT 검증

                // JWT에서 사용자 정보 (idx) 추출
                Long memberId = Long.parseLong(jwt.getClaimAsString("idx"));

                // Member 엔티티 조회
                Member member = memberRepository.findById(memberId)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found with idx: " + memberId));

                // MemberDetails 객체 생성 및 인증 객체 설정
                MembersDetails memberDetails = new MembersDetails(member);
                Authentication authentication = new UsernamePasswordAuthenticationToken(
                        memberDetails, null, memberDetails.getAuthorities());

                // SecurityContextHolder에 인증 정보 설정
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JwtException e) {
                // JWT 검증 실패 처리 (예: 401 Unauthorized 응답)
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT token");
                return;
            }
        }

        filterChain.doFilter(request, response); // 다음 필터 또는 컨트롤러로 요청 전달
    }

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7); // "Bearer " 이후의 토큰 값 반환
        }
        return null;
    }
}
