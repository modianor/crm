<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!-- 客户构成分析 -->
<div id="customerConstitute">
    <div>
        <form class="form-inline">
            <div class="form-group">
                <select name="condit" id="form-control">
                    <option value="0" selected="selected">按等级</option>
                    <option value="1">按信用度</option>
                    <option value="2">按满意度</option>
                </select>

            </div>

            <input class="btn btn-default search" type="button" value="查询">
        </form>
    </div>
    <div id="container">

    </div>

</div>
<style>
    #customerConstitute {
        width: 1140px;
        overflow: hidden;
    }

    .form-inline {
        margin-top: 15px;
        margin-left: 10px;
        margin-bottom: 20px;
    }

    .search {
        margin-left: 50px;
    }

    .table th, td {
        text-align: center;
    }

    #add {
        margin-left: 250px;
    }

    #toAdd {
        margin-top: 50px;
    }

    #deleteModal {
        margin-top: 100px;
    }

    #container {
        border: 1px solid #ccc;
        min-height: 430px;
        width: 97%;
        margin: 10px;
        box-shadow: 10px 10px 5px #ccc;
    }
</style>
<script type="text/javascript">

    $(function () {
        var url = "reportForms/getConstitute";
        $.get(url, {condition: 0}, function (data) {
            draw(data);
        });
        //查询按钮
        $(".search").on("click", function () {
            var condit = $("select[name=condit] option:selected").val();

            $.get(url, {condition: condit}, function (data) {
                draw(data);
            });
        });


    })

    function draw(data) {
        var chart = Highcharts.chart('container', {
            chart: {
                type: 'column'
            },
            title: {
                text: '客户构成分析'
            },
            subtitle: {
                text: ''
            },
            xAxis: {
                type: 'category'
            },
            yAxis: {
                title: {
                    text: ''
                }
            },
            legend: {
                enabled: false
            },
            plotOptions: {
                series: {
                    borderWidth: 0,
                    dataLabels: {
                        enabled: true,
                        format: '{point.y:.2f}%'
                    }
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
                pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
            },
            series: [{
                name: '客户数量',
                colorByPoint: true,
                data: data
            }]
        });
    }
</script>