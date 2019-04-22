package com.zimu.view.dict;

import com.zimu.common.view.BaseView;
import com.zimu.common.view.ViewNames;
import com.zimu.common.view.annotation.ViewName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@ViewName(ViewNames.DICT_LIST)
public class DictListView implements BaseView {

}
