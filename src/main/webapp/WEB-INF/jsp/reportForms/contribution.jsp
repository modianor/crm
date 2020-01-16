<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<!-- 客户贡献分析 -->
<div id="customerContribution">
    <div>
        <form class="form-inline">
            <div class="form-group">
                <select name="region" id="" class="form-control">
                    <option value="">请选择所属地区</option>
                    <option value="华中">华中</option>
                    <option value="华北">华北</option>
                    <option value="华南">华南</option>
                    <option value="华东">华东</option>
                </select>
            </div>
            <input class="btn btn-default search" type="button" value="查询">
        </form>
    </div>
    <div id="container">

    </div>

</div>
<style>
    #customerContribution {
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
        var url = "reportForms/getContribution";
        $.get(url, function (data) {
            draw(data);
        });

        //查询
        $(".search").on("click", function () {
            var region = $("select[name=region] option:selected").val();
            var url = "reportForms/getContributionByRegion";
            $.get(url, {region: region}, function (data) {
                console.log(data);
                draw(data);
            })
        });
    })

    function draw(data) {
        var chart = Highcharts.chart('container', {
            chart: {
                type: 'column'
            },
            title: {
                text: '客户贡献分析'
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
                name: '客户贡献',
                colorByPoint: true,
                data: data
            }]
        });
    }
</script>