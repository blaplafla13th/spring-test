package test.ngocpt.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ListResponse<T> extends BaseResponse{
    private Integer currentPage;
    private Integer maxPage;
    private Integer pageSize;
    private List<T> data;
}
