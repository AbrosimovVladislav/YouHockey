package ru.yourhockey.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;


@Getter
@RequiredArgsConstructor
public class ReviewDto {
	@NonNull private final int mark;
	@NonNull private final String pros;
	@NonNull private final String cons;
	@NonNull private final String comment;
	@Nullable private final Long shopId;
	@Nullable private final Long productId;
	@Nullable private final Long userId; // TODO: nullable due to not implemented accounting
}
