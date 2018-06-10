package cn.lframe.user.util;


import cn.lframe.user.enums.ResultEnum;
import cn.lframe.user.vo.ResultVO;

/**
 * @author Lframe
 * @create2018 -05 -03 -10:48
 */
public class ResultVOUtil {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMeg("成功");
        resultVO.setData(object);
        return resultVO;
    }
    public static ResultVO success() {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMeg("成功");
        return resultVO;
    }

    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(resultEnum.getCode());
        resultVO.setMeg(resultEnum.getMessage());
        return resultVO;
    }

}
