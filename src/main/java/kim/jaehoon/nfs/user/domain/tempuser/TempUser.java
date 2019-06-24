package kim.jaehoon.nfs.user.domain.tempuser;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class TempUser extends BaseTimeEntity{
}
