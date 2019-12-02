<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2019/11/20
  Time: 21:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <jsp:include page="WEB-INF/commons/commons.jsp"></jsp:include>
</head>
<body>
<div class="container col-md-12">
    <div class="panel panel-info">
        <div class="panel-heading">
            <h4>商品展示</h4>
            <div align="right">
                <button type="button" class="btn" onclick="addShop()">
                    <span class="glyphicon glyphicon-plus-sign"></span>
                </button>
            </div>
        </div>
        <div class="panel-body">
            <table id="shopTable" class="table table-bordered table-striped"></table>
        </div>
    </div>
</div>

<script type="text/javascript">
    //FileInput上传图片
    function initFileInput() {
        $("#shopPhoto").fileinput({
            language: 'zh',
            uploadUrl: "uploadFile",
            showCaption: false,//是否显示标题,就是那个文本框
            dropZoneEnabled: false,//是否显示拖拽区域，默认不写为true，但是会占用很大区域
        }).on("fileuploaded", function (event, result, previewId, index) {
            var url = result.response.data;
            /*alert(url);*/
            $("#photoUrl").val(url);
        });
    }

    var shopTable = $("#shopTable").DataTable({
        "serverSide": true,//true是服务器模式，false是客户端模式
        "lengthMenu": [5, 10, 15],
        "ajax": {
            url: "http://localhost:8090/shop/queryShopList",
            type: "get",
            "dataSrc": function (result) {
                return result.data;
            },
        },
        "columns": [
            {
                "data": "id", "title": "<input type=\"checkbox\">", render: function (data, type, row, meta) {
                    return '<input type="checkbox" name="id" value="'+data+'">';
                }
            },
            {"data": "name", "title": "商品名称"},
            {"data": "telPhone", "title": "联系电话"},
            {
                data: "imgUrl", "title": "品牌LOGO", render: function (data, type, row, meta) {
                    var url = "/" + data;
                    return "<img src='" + url + "'style='width: 40px'/>";
                }
            },
            {"data": "webSite", "title": "品牌网站"},
            {"data": "typeName", "title": "品牌类型"},
            {
                data: "updateTime", "title": "修改时间", render: function (data, type, row, meta) {
                    if (data != null) {
                        return new Date(data).Format("yyyy-MM-dd");
                    }
                    return "";
                }
            },
            {
                "data": "status", "title": "品牌状态", render: function (data, type, row, meta) {
                    return data == 1 ? "公开" :"不公开";
                }
            },
            {
                "data": "id", "title": "操作", render: function (data, type, row, meta) {
                    return '<button type="button" class="btn" onclick="updateShop(\'' + data + '\')">' +
                        '<span class="glyphicon glyphicon-edit"></span></button>'
                        +'<button type="button" class="btn" onclick="deleteShop(\'' + data + '\')">' +
                        '<span class="glyphicon glyphicon-remove"></span></button>'


                }
            },

        ],
        "language": {
            "url": "<%=request.getContextPath()%>/commons/datatable/Chinese.json"
        }
    });

    //新增
    function addShop() {
        var addHtml = "";
        $.post({
            url: "toAddShop",
            dataType: "html",
            async: false,
            success: function (result) {
                addHtml = result;
            }
        })
        //弹窗
        bootbox.confirm({
            message: addHtml,
            title: "正在添加商品",
            buttons: {
                cancel: {
                    label: "NO"
                },
                confirm: {
                    label: "YES",
                }
            },
            callback: function (result) {
                if (result) {
                    $("#shopForm").bootstrapValidator("validate");
                    var checkingStatus = $("#shopForm").data("bootstrapValidator").isValid();
                    if (checkingStatus) {
                        $.post({
                            url: "addShop",
                            data: $("#shopForm").serialize(),
                            dataType: "json",
                            success: function (result) {
                                bootbox.alert("保存成功");
                                $("#shopTable").dataTable()._fnDraw(false);
                            }

                        })
                    }else {
                        return false;
                    }
                }
            }
        })
        initFileInput();
    }

    /*修改*/
    function updateShop(id) {
        var updateHtml = "";
        alert(id);
        $.post({
            url: "getShopById",
            dataType: "html",
            async: false,
            data: {
                id:id
            },
            success: function (result) {
                updateHtml = result;
            }
        })
        //弹窗
        bootbox.confirm({
            message: updateHtml,
            title: "正在修改商品",
            buttons: {
                cancel: {
                    label: "NO"
                },
                confirm: {
                    label: "YES"
                }
            },
            callback: function (result) {
                if (result) {
                    $.post({
                        url: "updateShop",
                        data: $("#shopForm").serialize(),
                        dataType: "json",
                        success: function () {
                            bootbox.alert("修改存成功");
                            $("#shopTable").dataTable()._fnDraw(false);
                        }
                    })
                }
            }
        })
    }

</script>
</body>
</html>
