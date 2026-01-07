package com.sama.maint.service;

import com.core4ct.DTO.UserDTO;
import com.core4ct.support.Pagination;
import com.sama.api.ledger.bean.ConstructionDO;
import com.sama.api.ledger.bean.ConstructionManualDO;
import com.sama.api.ledger.bean.EngineerProjectDO;
import com.sama.maint.object.dto.CellUpdateMessageDTO;
import com.sama.maint.object.vo.*;

import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface WebSocketService {

    WebSocketResponseVO<?> initConnection(UserDTO userDTO);

    WebSocketResponseVO<?> addRow(String sheetName, Object row, UserDTO userDTO) throws Exception;

    WebSocketResponseVO<?> deleteRow(String sheetName, Object newData, UserDTO userDTO) throws Exception;

    WebSocketResponseVO<?> lockCell(CellUpdateMessageDTO message, UserDTO userDTO) throws Exception;

    WebSocketResponseVO<?> unlockCell(CellUpdateMessageDTO message, UserDTO userDTO) throws Exception;

    WebSocketResponseVO<?> updateCell(String sheetName, Object newData, UserDTO userDTO) throws Exception;

    WebSocketResponseVO<?> getTableData( String sheetName, UserDTO userDTO)throws Exception ;

    WebSocketResponseVO<?> userLeave(UserDTO userDTO)throws Exception ;

    void upload(UserDTO userDTO, List<EngineerProjectDO> sheetEngineerProject1231, List<EngineerProjectDO> sheetEngineerProject0930, List<EngineerProjectDO> sheetEngineerProject0630, List<ConstructionDO> sheetConstructionProject, Integer number, Map<String, Object> sheetMap) throws Exception;

    void downloadFile(HttpServletResponse response) throws Exception;


    void submitEngineerProject1231(UserDTO userDTO) throws Exception;

    void submitEngineerProject0930(UserDTO userDTO) throws Exception;

    void submitEngineerProject0630(UserDTO userDTO) throws Exception;

    void submitConstructionProject(UserDTO userDTO) throws Exception;

    Pagination<EngineerProjectDO> engineerProjectPage(UserDTO userDTO,EngineerProjectVO engineerProjectVO)throws Exception;

    Pagination<ConstructionDO> constructionProjectPage(UserDTO userDTO, ConstructionProjectVO constructionDO)throws Exception;

    Object getFromTableDataMap(String key);

    String getProvinceCodes(UserDTO userDTO) throws Exception;

    void submitConstructionManualProject(UserDTO userDTO) throws Exception;

    ConstructionManualDO manualPage(UserDTO userDTO, ConstructionManualDO constructionManualDO) throws Exception;
}