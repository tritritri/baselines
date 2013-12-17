
package org.wattalyst.services.secured;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.wattalyst.services.secured package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _LoginResponse_QNAME = new QName("http://secured.services.wattalyst.org/", "loginResponse");
    private final static QName _GetSubLocations_QNAME = new QName("http://secured.services.wattalyst.org/", "getSubLocations");
    private final static QName _GetLocationSensorsResponse_QNAME = new QName("http://secured.services.wattalyst.org/", "getLocationSensorsResponse");
    private final static QName _GetLastValueForSensorResponse_QNAME = new QName("http://secured.services.wattalyst.org/", "getLastValueForSensorResponse");
    private final static QName _GetLocationSensorsByCategory_QNAME = new QName("http://secured.services.wattalyst.org/", "getLocationSensorsByCategory");
    private final static QName _GetSensor_QNAME = new QName("http://secured.services.wattalyst.org/", "getSensor");
    private final static QName _GetLocationSensorsByAnnotation_QNAME = new QName("http://secured.services.wattalyst.org/", "getLocationSensorsByAnnotation");
    private final static QName _GetLocation_QNAME = new QName("http://secured.services.wattalyst.org/", "getLocation");
    private final static QName _GetLocationSensors_QNAME = new QName("http://secured.services.wattalyst.org/", "getLocationSensors");
    private final static QName _GetAllLocationsResponse_QNAME = new QName("http://secured.services.wattalyst.org/", "getAllLocationsResponse");
    private final static QName _GetSubLocationsResponse_QNAME = new QName("http://secured.services.wattalyst.org/", "getSubLocationsResponse");
    private final static QName _GetSensorByLocationAndSensorTypeResponse_QNAME = new QName("http://secured.services.wattalyst.org/", "getSensorByLocationAndSensorTypeResponse");
    private final static QName _GetValuesForSensorByRange_QNAME = new QName("http://secured.services.wattalyst.org/", "getValuesForSensorByRange");
    private final static QName _GetLocationResponse_QNAME = new QName("http://secured.services.wattalyst.org/", "getLocationResponse");
    private final static QName _GetLastValueForSensor_QNAME = new QName("http://secured.services.wattalyst.org/", "getLastValueForSensor");
    private final static QName _GetSensorResponse_QNAME = new QName("http://secured.services.wattalyst.org/", "getSensorResponse");
    private final static QName _GetExportedData_QNAME = new QName("http://secured.services.wattalyst.org/", "getExportedData");
    private final static QName _GetValuesForSensorByRangeResponse_QNAME = new QName("http://secured.services.wattalyst.org/", "getValuesForSensorByRangeResponse");
    private final static QName _GetExportedDataResponse_QNAME = new QName("http://secured.services.wattalyst.org/", "getExportedDataResponse");
    private final static QName _GetAllLocations_QNAME = new QName("http://secured.services.wattalyst.org/", "getAllLocations");
    private final static QName _GetValuesForSensor_QNAME = new QName("http://secured.services.wattalyst.org/", "getValuesForSensor");
    private final static QName _GetSensorByLocationAndSensorType_QNAME = new QName("http://secured.services.wattalyst.org/", "getSensorByLocationAndSensorType");
    private final static QName _GetLocationSensorsByAnnotationResponse_QNAME = new QName("http://secured.services.wattalyst.org/", "getLocationSensorsByAnnotationResponse");
    private final static QName _Login_QNAME = new QName("http://secured.services.wattalyst.org/", "login");
    private final static QName _GetValuesForSensorResponse_QNAME = new QName("http://secured.services.wattalyst.org/", "getValuesForSensorResponse");
    private final static QName _GetLocationSensorsByCategoryResponse_QNAME = new QName("http://secured.services.wattalyst.org/", "getLocationSensorsByCategoryResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.wattalyst.services.secured
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DrStatusDto }
     * 
     */
    public DrStatusDto createDrStatusDto() {
        return new DrStatusDto();
    }

    /**
     * Create an instance of {@link DrStatusDto.History }
     * 
     */
    public DrStatusDto.History createDrStatusDtoHistory() {
        return new DrStatusDto.History();
    }

    /**
     * Create an instance of {@link GetValuesForSensorByRange }
     * 
     */
    public GetValuesForSensorByRange createGetValuesForSensorByRange() {
        return new GetValuesForSensorByRange();
    }

    /**
     * Create an instance of {@link GetSensorByLocationAndSensorTypeResponse }
     * 
     */
    public GetSensorByLocationAndSensorTypeResponse createGetSensorByLocationAndSensorTypeResponse() {
        return new GetSensorByLocationAndSensorTypeResponse();
    }

    /**
     * Create an instance of {@link GetLastValueForSensor }
     * 
     */
    public GetLastValueForSensor createGetLastValueForSensor() {
        return new GetLastValueForSensor();
    }

    /**
     * Create an instance of {@link GetLocationResponse }
     * 
     */
    public GetLocationResponse createGetLocationResponse() {
        return new GetLocationResponse();
    }

    /**
     * Create an instance of {@link GetExportedData }
     * 
     */
    public GetExportedData createGetExportedData() {
        return new GetExportedData();
    }

    /**
     * Create an instance of {@link GetSensorResponse }
     * 
     */
    public GetSensorResponse createGetSensorResponse() {
        return new GetSensorResponse();
    }

    /**
     * Create an instance of {@link GetLocationSensorsByCategoryResponse }
     * 
     */
    public GetLocationSensorsByCategoryResponse createGetLocationSensorsByCategoryResponse() {
        return new GetLocationSensorsByCategoryResponse();
    }

    /**
     * Create an instance of {@link GetValuesForSensorResponse }
     * 
     */
    public GetValuesForSensorResponse createGetValuesForSensorResponse() {
        return new GetValuesForSensorResponse();
    }

    /**
     * Create an instance of {@link Login }
     * 
     */
    public Login createLogin() {
        return new Login();
    }

    /**
     * Create an instance of {@link GetLocationSensorsByAnnotationResponse }
     * 
     */
    public GetLocationSensorsByAnnotationResponse createGetLocationSensorsByAnnotationResponse() {
        return new GetLocationSensorsByAnnotationResponse();
    }

    /**
     * Create an instance of {@link GetSensorByLocationAndSensorType }
     * 
     */
    public GetSensorByLocationAndSensorType createGetSensorByLocationAndSensorType() {
        return new GetSensorByLocationAndSensorType();
    }

    /**
     * Create an instance of {@link GetValuesForSensor }
     * 
     */
    public GetValuesForSensor createGetValuesForSensor() {
        return new GetValuesForSensor();
    }

    /**
     * Create an instance of {@link GetExportedDataResponse }
     * 
     */
    public GetExportedDataResponse createGetExportedDataResponse() {
        return new GetExportedDataResponse();
    }

    /**
     * Create an instance of {@link GetAllLocations }
     * 
     */
    public GetAllLocations createGetAllLocations() {
        return new GetAllLocations();
    }

    /**
     * Create an instance of {@link GetValuesForSensorByRangeResponse }
     * 
     */
    public GetValuesForSensorByRangeResponse createGetValuesForSensorByRangeResponse() {
        return new GetValuesForSensorByRangeResponse();
    }

    /**
     * Create an instance of {@link GetLocationSensorsByCategory }
     * 
     */
    public GetLocationSensorsByCategory createGetLocationSensorsByCategory() {
        return new GetLocationSensorsByCategory();
    }

    /**
     * Create an instance of {@link GetLastValueForSensorResponse }
     * 
     */
    public GetLastValueForSensorResponse createGetLastValueForSensorResponse() {
        return new GetLastValueForSensorResponse();
    }

    /**
     * Create an instance of {@link GetLocationSensorsResponse }
     * 
     */
    public GetLocationSensorsResponse createGetLocationSensorsResponse() {
        return new GetLocationSensorsResponse();
    }

    /**
     * Create an instance of {@link LoginResponse }
     * 
     */
    public LoginResponse createLoginResponse() {
        return new LoginResponse();
    }

    /**
     * Create an instance of {@link GetSubLocations }
     * 
     */
    public GetSubLocations createGetSubLocations() {
        return new GetSubLocations();
    }

    /**
     * Create an instance of {@link GetSensor }
     * 
     */
    public GetSensor createGetSensor() {
        return new GetSensor();
    }

    /**
     * Create an instance of {@link GetLocation }
     * 
     */
    public GetLocation createGetLocation() {
        return new GetLocation();
    }

    /**
     * Create an instance of {@link GetLocationSensorsByAnnotation }
     * 
     */
    public GetLocationSensorsByAnnotation createGetLocationSensorsByAnnotation() {
        return new GetLocationSensorsByAnnotation();
    }

    /**
     * Create an instance of {@link GetSubLocationsResponse }
     * 
     */
    public GetSubLocationsResponse createGetSubLocationsResponse() {
        return new GetSubLocationsResponse();
    }

    /**
     * Create an instance of {@link GetAllLocationsResponse }
     * 
     */
    public GetAllLocationsResponse createGetAllLocationsResponse() {
        return new GetAllLocationsResponse();
    }

    /**
     * Create an instance of {@link GetLocationSensors }
     * 
     */
    public GetLocationSensors createGetLocationSensors() {
        return new GetLocationSensors();
    }

    /**
     * Create an instance of {@link StringResultContainer }
     * 
     */
    public StringResultContainer createStringResultContainer() {
        return new StringResultContainer();
    }

    /**
     * Create an instance of {@link ConstraintDto }
     * 
     */
    public ConstraintDto createConstraintDto() {
        return new ConstraintDto();
    }

    /**
     * Create an instance of {@link KpiPerformanceDto }
     * 
     */
    public KpiPerformanceDto createKpiPerformanceDto() {
        return new KpiPerformanceDto();
    }

    /**
     * Create an instance of {@link MissingValueDto }
     * 
     */
    public MissingValueDto createMissingValueDto() {
        return new MissingValueDto();
    }

    /**
     * Create an instance of {@link BooleanValueDto }
     * 
     */
    public BooleanValueDto createBooleanValueDto() {
        return new BooleanValueDto();
    }

    /**
     * Create an instance of {@link MetaDatumDto }
     * 
     */
    public MetaDatumDto createMetaDatumDto() {
        return new MetaDatumDto();
    }

    /**
     * Create an instance of {@link DrSignalDto }
     * 
     */
    public DrSignalDto createDrSignalDto() {
        return new DrSignalDto();
    }

    /**
     * Create an instance of {@link TextValueDto }
     * 
     */
    public TextValueDto createTextValueDto() {
        return new TextValueDto();
    }

    /**
     * Create an instance of {@link BaselineDto }
     * 
     */
    public BaselineDto createBaselineDto() {
        return new BaselineDto();
    }

    /**
     * Create an instance of {@link SensorResultContainer }
     * 
     */
    public SensorResultContainer createSensorResultContainer() {
        return new SensorResultContainer();
    }

    /**
     * Create an instance of {@link QuantizationDto }
     * 
     */
    public QuantizationDto createQuantizationDto() {
        return new QuantizationDto();
    }

    /**
     * Create an instance of {@link SensorDto }
     * 
     */
    public SensorDto createSensorDto() {
        return new SensorDto();
    }

    /**
     * Create an instance of {@link LocationListResultContainer }
     * 
     */
    public LocationListResultContainer createLocationListResultContainer() {
        return new LocationListResultContainer();
    }

    /**
     * Create an instance of {@link FeedbackDto }
     * 
     */
    public FeedbackDto createFeedbackDto() {
        return new FeedbackDto();
    }

    /**
     * Create an instance of {@link NumericValueDto }
     * 
     */
    public NumericValueDto createNumericValueDto() {
        return new NumericValueDto();
    }

    /**
     * Create an instance of {@link LocationResultContainer }
     * 
     */
    public LocationResultContainer createLocationResultContainer() {
        return new LocationResultContainer();
    }

    /**
     * Create an instance of {@link ScheduleDto }
     * 
     */
    public ScheduleDto createScheduleDto() {
        return new ScheduleDto();
    }

    /**
     * Create an instance of {@link ValueListResultContainer }
     * 
     */
    public ValueListResultContainer createValueListResultContainer() {
        return new ValueListResultContainer();
    }

    /**
     * Create an instance of {@link TimeIntervalDto }
     * 
     */
    public TimeIntervalDto createTimeIntervalDto() {
        return new TimeIntervalDto();
    }

    /**
     * Create an instance of {@link RecipientContextDto }
     * 
     */
    public RecipientContextDto createRecipientContextDto() {
        return new RecipientContextDto();
    }

    /**
     * Create an instance of {@link UnitDto }
     * 
     */
    public UnitDto createUnitDto() {
        return new UnitDto();
    }

    /**
     * Create an instance of {@link SensorListResultContainer }
     * 
     */
    public SensorListResultContainer createSensorListResultContainer() {
        return new SensorListResultContainer();
    }

    /**
     * Create an instance of {@link ByteResultContainer }
     * 
     */
    public ByteResultContainer createByteResultContainer() {
        return new ByteResultContainer();
    }

    /**
     * Create an instance of {@link ValueResultContainer }
     * 
     */
    public ValueResultContainer createValueResultContainer() {
        return new ValueResultContainer();
    }

    /**
     * Create an instance of {@link DrUseCaseDto }
     * 
     */
    public DrUseCaseDto createDrUseCaseDto() {
        return new DrUseCaseDto();
    }

    /**
     * Create an instance of {@link TaskDto }
     * 
     */
    public TaskDto createTaskDto() {
        return new TaskDto();
    }

    /**
     * Create an instance of {@link LocationDto }
     * 
     */
    public LocationDto createLocationDto() {
        return new LocationDto();
    }

    /**
     * Create an instance of {@link DrStatusDto.History.Entry }
     * 
     */
    public DrStatusDto.History.Entry createDrStatusDtoHistoryEntry() {
        return new DrStatusDto.History.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LoginResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "loginResponse")
    public JAXBElement<LoginResponse> createLoginResponse(LoginResponse value) {
        return new JAXBElement<LoginResponse>(_LoginResponse_QNAME, LoginResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSubLocations }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getSubLocations")
    public JAXBElement<GetSubLocations> createGetSubLocations(GetSubLocations value) {
        return new JAXBElement<GetSubLocations>(_GetSubLocations_QNAME, GetSubLocations.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocationSensorsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getLocationSensorsResponse")
    public JAXBElement<GetLocationSensorsResponse> createGetLocationSensorsResponse(GetLocationSensorsResponse value) {
        return new JAXBElement<GetLocationSensorsResponse>(_GetLocationSensorsResponse_QNAME, GetLocationSensorsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLastValueForSensorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getLastValueForSensorResponse")
    public JAXBElement<GetLastValueForSensorResponse> createGetLastValueForSensorResponse(GetLastValueForSensorResponse value) {
        return new JAXBElement<GetLastValueForSensorResponse>(_GetLastValueForSensorResponse_QNAME, GetLastValueForSensorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocationSensorsByCategory }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getLocationSensorsByCategory")
    public JAXBElement<GetLocationSensorsByCategory> createGetLocationSensorsByCategory(GetLocationSensorsByCategory value) {
        return new JAXBElement<GetLocationSensorsByCategory>(_GetLocationSensorsByCategory_QNAME, GetLocationSensorsByCategory.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSensor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getSensor")
    public JAXBElement<GetSensor> createGetSensor(GetSensor value) {
        return new JAXBElement<GetSensor>(_GetSensor_QNAME, GetSensor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocationSensorsByAnnotation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getLocationSensorsByAnnotation")
    public JAXBElement<GetLocationSensorsByAnnotation> createGetLocationSensorsByAnnotation(GetLocationSensorsByAnnotation value) {
        return new JAXBElement<GetLocationSensorsByAnnotation>(_GetLocationSensorsByAnnotation_QNAME, GetLocationSensorsByAnnotation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getLocation")
    public JAXBElement<GetLocation> createGetLocation(GetLocation value) {
        return new JAXBElement<GetLocation>(_GetLocation_QNAME, GetLocation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocationSensors }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getLocationSensors")
    public JAXBElement<GetLocationSensors> createGetLocationSensors(GetLocationSensors value) {
        return new JAXBElement<GetLocationSensors>(_GetLocationSensors_QNAME, GetLocationSensors.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLocationsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getAllLocationsResponse")
    public JAXBElement<GetAllLocationsResponse> createGetAllLocationsResponse(GetAllLocationsResponse value) {
        return new JAXBElement<GetAllLocationsResponse>(_GetAllLocationsResponse_QNAME, GetAllLocationsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSubLocationsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getSubLocationsResponse")
    public JAXBElement<GetSubLocationsResponse> createGetSubLocationsResponse(GetSubLocationsResponse value) {
        return new JAXBElement<GetSubLocationsResponse>(_GetSubLocationsResponse_QNAME, GetSubLocationsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSensorByLocationAndSensorTypeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getSensorByLocationAndSensorTypeResponse")
    public JAXBElement<GetSensorByLocationAndSensorTypeResponse> createGetSensorByLocationAndSensorTypeResponse(GetSensorByLocationAndSensorTypeResponse value) {
        return new JAXBElement<GetSensorByLocationAndSensorTypeResponse>(_GetSensorByLocationAndSensorTypeResponse_QNAME, GetSensorByLocationAndSensorTypeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetValuesForSensorByRange }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getValuesForSensorByRange")
    public JAXBElement<GetValuesForSensorByRange> createGetValuesForSensorByRange(GetValuesForSensorByRange value) {
        return new JAXBElement<GetValuesForSensorByRange>(_GetValuesForSensorByRange_QNAME, GetValuesForSensorByRange.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getLocationResponse")
    public JAXBElement<GetLocationResponse> createGetLocationResponse(GetLocationResponse value) {
        return new JAXBElement<GetLocationResponse>(_GetLocationResponse_QNAME, GetLocationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLastValueForSensor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getLastValueForSensor")
    public JAXBElement<GetLastValueForSensor> createGetLastValueForSensor(GetLastValueForSensor value) {
        return new JAXBElement<GetLastValueForSensor>(_GetLastValueForSensor_QNAME, GetLastValueForSensor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSensorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getSensorResponse")
    public JAXBElement<GetSensorResponse> createGetSensorResponse(GetSensorResponse value) {
        return new JAXBElement<GetSensorResponse>(_GetSensorResponse_QNAME, GetSensorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetExportedData }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getExportedData")
    public JAXBElement<GetExportedData> createGetExportedData(GetExportedData value) {
        return new JAXBElement<GetExportedData>(_GetExportedData_QNAME, GetExportedData.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetValuesForSensorByRangeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getValuesForSensorByRangeResponse")
    public JAXBElement<GetValuesForSensorByRangeResponse> createGetValuesForSensorByRangeResponse(GetValuesForSensorByRangeResponse value) {
        return new JAXBElement<GetValuesForSensorByRangeResponse>(_GetValuesForSensorByRangeResponse_QNAME, GetValuesForSensorByRangeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetExportedDataResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getExportedDataResponse")
    public JAXBElement<GetExportedDataResponse> createGetExportedDataResponse(GetExportedDataResponse value) {
        return new JAXBElement<GetExportedDataResponse>(_GetExportedDataResponse_QNAME, GetExportedDataResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllLocations }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getAllLocations")
    public JAXBElement<GetAllLocations> createGetAllLocations(GetAllLocations value) {
        return new JAXBElement<GetAllLocations>(_GetAllLocations_QNAME, GetAllLocations.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetValuesForSensor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getValuesForSensor")
    public JAXBElement<GetValuesForSensor> createGetValuesForSensor(GetValuesForSensor value) {
        return new JAXBElement<GetValuesForSensor>(_GetValuesForSensor_QNAME, GetValuesForSensor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSensorByLocationAndSensorType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getSensorByLocationAndSensorType")
    public JAXBElement<GetSensorByLocationAndSensorType> createGetSensorByLocationAndSensorType(GetSensorByLocationAndSensorType value) {
        return new JAXBElement<GetSensorByLocationAndSensorType>(_GetSensorByLocationAndSensorType_QNAME, GetSensorByLocationAndSensorType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocationSensorsByAnnotationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getLocationSensorsByAnnotationResponse")
    public JAXBElement<GetLocationSensorsByAnnotationResponse> createGetLocationSensorsByAnnotationResponse(GetLocationSensorsByAnnotationResponse value) {
        return new JAXBElement<GetLocationSensorsByAnnotationResponse>(_GetLocationSensorsByAnnotationResponse_QNAME, GetLocationSensorsByAnnotationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Login }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "login")
    public JAXBElement<Login> createLogin(Login value) {
        return new JAXBElement<Login>(_Login_QNAME, Login.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetValuesForSensorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getValuesForSensorResponse")
    public JAXBElement<GetValuesForSensorResponse> createGetValuesForSensorResponse(GetValuesForSensorResponse value) {
        return new JAXBElement<GetValuesForSensorResponse>(_GetValuesForSensorResponse_QNAME, GetValuesForSensorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLocationSensorsByCategoryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://secured.services.wattalyst.org/", name = "getLocationSensorsByCategoryResponse")
    public JAXBElement<GetLocationSensorsByCategoryResponse> createGetLocationSensorsByCategoryResponse(GetLocationSensorsByCategoryResponse value) {
        return new JAXBElement<GetLocationSensorsByCategoryResponse>(_GetLocationSensorsByCategoryResponse_QNAME, GetLocationSensorsByCategoryResponse.class, null, value);
    }

}
