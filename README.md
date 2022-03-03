# Springboot_blog

1차 fix.

lombok의 @Data 애노테이션은 Getter/Setter, ToString, EqualsAndHashCode 그리고 RequiredArgsConstructor를 갖고 있어 양 쪽 모두 참조하고 있다면 
순환 참조가 발생할 수 있습니다. 또한 setter로 인해 외부에서 너무 쉽게 필드에 접근해 데이터를 변경할 수 있기 때문에 @Data → @Getter 만을 사용하게 변경하였습니다.

```
@Autowired
private UserService userservice;

```

2차 fix.

final을 붙이지 않아 불변성이 보장되지도 않고 테스트 시에도 불편함을 겪을 수 있습니다. 그리고 가장 큰 위험성은 순환 참조에 대해 컴파일이 아닌
런타임 단계에서 발생하기 때문에 (2.6.x 이전 버전) 이러한 필드 주입은 위험하다고 판단하여 @RequiredArgsConstructor로 변경하여 생성자 주입으로 보다 
안전하고 실용적인 방법으로 의존성을 주입하였습니다.
