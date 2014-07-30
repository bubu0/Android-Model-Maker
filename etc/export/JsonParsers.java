	public static ArrayList<ActionTypeContentValues> parseJsonActionType(JSONArray joInput, Context ctxt, int extId) { 
		ArrayList<ActionTypeContentValues> list = new ArrayList<ActionTypeContentValues>(); 
		try { 
			ActionTypeContentValues val; 
			for (int i = 0; i < joInput.length(); i++) { 
				JSONObject joInside = joInput.getJSONObject(i);
				val = new ActionTypeContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("created_by") == false)
					val.putCreatedBy(Utils.extractIdFromUri(joInside.getString("created_by"))); 
				if( joInside.isNull("state") == false)
					val.putState(joInside.getString("state")); 
				if( joInside.isNull("is_useraction") == false)
					val.putIsUseraction(joInside.getBoolean("is_useraction")); 
				if( joInside.isNull("lang") == false)
					val.putLang(joInside.getString("lang")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("market") == false)
					val.putMarket(Utils.extractIdFromUri(joInside.getString("market"))); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 

				JSONArray jArray;
				if( joInside.isNull("medias") == false) {
					jArray = joInside.getJSONArray("medias");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						ActionTypeMediasContentValues ActionTypeMediasCv1 = new ActionTypeMediasContentValues();
						ActionTypeMediasCv1.putActionTypeId(id);
						ActionTypeMediasCv1.putMediasId(v);
						ctxt.getContentResolver().insert(ActionTypeMediasColumns.CONTENT_URI, ActionTypeMediasCv1.values());
					}

				}
 
				if( joInside.isNull("name") == false)
					val.putName(joInside.getString("name")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("parameter_id") == false)
					val.putParameterId(joInside.getString("parameter_id")); 

				ActionTypeActionsContentValues actionsCv1 = new ActionTypeActionsContentValues();
				actionsCv1.putActionTypeId(id);
				actionsCv1.putActionsId(extId);
				ctxt.getContentResolver().insert(ActionTypeActionsColumns.CONTENT_URI, actionsCv1.values());
 
				if( joInside.isNull("available_transitions") == false) {
					jArray = joInside.getJSONArray("available_transitions");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						String v = jArray.getString(j);
						AvailableTransitionsContentValues availableTransitionsCv3 = new AvailableTransitionsContentValues();
						availableTransitionsCv3.putContentsId(id);
						availableTransitionsCv3.putAvailableTransitions(v);
						ctxt.getContentResolver().insert(AvailableTransitionsColumns.CONTENT_URI, availableTransitionsCv3.values());
					}

				}
 

				if(joInside.isNull("extra_data") == false)
					parseJsonExtraData(joInside.getJSONObject("extra_data"), ctxt, id);
				if(joInside.isNull("created_by") == false)
					parseJsonCreatedBy(joInside.getJSONObject("created_by"), ctxt, id); 
				ctxt.getContentResolver().insert(ActionTypeColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<ExtraDataContentValues> parseJsonExtraData(JSONObject joInside, Context ctxt, int extId) { 
		ArrayList<ExtraDataContentValues> list = new ArrayList<ExtraDataContentValues>(); 
		try { 
			ExtraDataContentValues val = new ExtraDataContentValues(); 

				ActionTypeExtraDataContentValues actionTypeCv0 = new ActionTypeExtraDataContentValues();
				actionTypeCv0.putExtraDataId(id);
				actionTypeCv0.putActionTypeId(extId);
				ctxt.getContentResolver().insert(ActionTypeExtraDataColumns.CONTENT_URI, actionTypeCv0.values());
 
				if( joInside.isNull("display") == false)
					val.putDisplay(joInside.getInt("display")); 

				ExtraDataNewsContentValues newsCv1 = new ExtraDataNewsContentValues();
				newsCv1.putExtraDataId(id);
				newsCv1.putNewsId(extId);
				ctxt.getContentResolver().insert(ExtraDataNewsColumns.CONTENT_URI, newsCv1.values());
 
				if( joInside.isNull("title_home") == false)
					val.putTitleHome(joInside.getString("title_home")); 
				if( joInside.isNull("body_home") == false)
					val.putBodyHome(joInside.getString("body_home")); 
				ctxt.getContentResolver().insert(ExtraDataColumns.CONTENT_URI, val.values()); 
			list.add(val);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<AppliancesContentValues> parseJsonAppliances(JSONObject jo, Context ctxt) { 
		ArrayList<AppliancesContentValues> list = new ArrayList<AppliancesContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					AppliancesSelection where = new AppliancesSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			AppliancesContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new AppliancesContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("technical_index") == false)
					val.putTechnicalIndex(joInside.getInt("technical_index")); 

				JSONArray jArray;
				if( joInside.isNull("terms") == false) {
					jArray = joInside.getJSONArray("terms");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						AppliancesTermsContentValues AppliancesTermsCv1 = new AppliancesTermsContentValues();
						AppliancesTermsCv1.putAppliancesId(id);
						AppliancesTermsCv1.putTermsId(v);
						ctxt.getContentResolver().insert(AppliancesTermsColumns.CONTENT_URI, AppliancesTermsCv1.values());
					}

				}
 
				if( joInside.isNull("name_bg") == false)
					val.putNameBg(joInside.getString("name_bg")); 
				if( joInside.isNull("name_de") == false)
					val.putNameDe(joInside.getString("name_de")); 
				if( joInside.isNull("name_ja") == false)
					val.putNameJa(joInside.getString("name_ja")); 
				if( joInside.isNull("lang") == false)
					val.putLang(joInside.getString("lang")); 
				if( joInside.isNull("name_uk") == false)
					val.putNameUk(joInside.getString("name_uk")); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("name_es") == false)
					val.putNameEs(joInside.getString("name_es")); 
				if( joInside.isNull("name_nl") == false)
					val.putNameNl(joInside.getString("name_nl")); 
				if( joInside.isNull("connectable") == false)
					val.putConnectable(joInside.getBoolean("connectable")); 
				if( joInside.isNull("binaries") == false) {
					jArray = joInside.getJSONArray("binaries");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						String v = jArray.getString(j);
						BinariesContentValues binariesCv1 = new BinariesContentValues();
						binariesCv1.putAppliancesId(id);
						binariesCv1.putBinaries(v);
						ctxt.getContentResolver().insert(BinariesColumns.CONTENT_URI, binariesCv1.values());
					}

				}
 
				if( joInside.isNull("name_en") == false)
					val.putNameEn(joInside.getString("name_en")); 
				if( joInside.isNull("name_fr") == false)
					val.putNameFr(joInside.getString("name_fr")); 
				if( joInside.isNull("reference") == false)
					val.putReference(joInside.getString("reference")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("market") == false)
					val.putMarket(Utils.extractIdFromUri(joInside.getString("market"))); 
				if( joInside.isNull("name_pt") == false)
					val.putNamePt(joInside.getString("name_pt")); 
				if( joInside.isNull("medias") == false) {
					jArray = joInside.getJSONArray("medias");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						AppliancesMediasContentValues AppliancesMediasCv3 = new AppliancesMediasContentValues();
						AppliancesMediasCv3.putAppliancesId(id);
						AppliancesMediasCv3.putMediasId(v);
						ctxt.getContentResolver().insert(AppliancesMediasColumns.CONTENT_URI, AppliancesMediasCv3.values());
					}

				}
 
				if( joInside.isNull("export_to") == false) {
					jArray = joInside.getJSONArray("export_to");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						String v = jArray.getString(j);
						ExportToContentValues exportToCv3 = new ExportToContentValues();
						exportToCv3.putAppliancesId(id);
						exportToCv3.putExportTo(v);
						ctxt.getContentResolver().insert(ExportToColumns.CONTENT_URI, exportToCv3.values());
					}

				}
 
				if( joInside.isNull("name_ru") == false)
					val.putNameRu(joInside.getString("name_ru")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("group") == false)
					val.putGroupDb(Utils.extractIdFromUri(joInside.getString("group"))); 
				if( joInside.isNull("name_it") == false)
					val.putNameIt(joInside.getString("name_it")); 
				if( joInside.isNull("name_ro") == false)
					val.putNameRo(joInside.getString("name_ro")); 

				if(joInside.isNull("created_by") == false)
					parseJsonCreatedBy(joInside.getJSONObject("created_by"), ctxt, id); 
				ctxt.getContentResolver().insert(AppliancesColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<CreatedByContentValues> parseJsonCreatedBy(JSONObject joInside, Context ctxt, int extId) { 
		ArrayList<CreatedByContentValues> list = new ArrayList<CreatedByContentValues>(); 
		try { 
			CreatedByContentValues val = new CreatedByContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 

				AppliancesCreatedByContentValues appliancesCv0 = new AppliancesCreatedByContentValues();
				appliancesCv0.putCreatedById(id);
				appliancesCv0.putAppliancesId(extId);
				ctxt.getContentResolver().insert(AppliancesCreatedByColumns.CONTENT_URI, appliancesCv0.values());
 
				if( joInside.isNull("username") == false)
					val.putUsername(joInside.getString("username")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 

				CreatedByKitchenWareContentValues kitchenWareCv1 = new CreatedByKitchenWareContentValues();
				kitchenWareCv1.putCreatedById(id);
				kitchenWareCv1.putKitchenWareId(extId);
				ctxt.getContentResolver().insert(CreatedByKitchenWareColumns.CONTENT_URI, kitchenWareCv1.values());
 

				CommentsCreatedByContentValues commentsCv2 = new CommentsCreatedByContentValues();
				commentsCv2.putCreatedById(id);
				commentsCv2.putCommentsId(extId);
				ctxt.getContentResolver().insert(CommentsCreatedByColumns.CONTENT_URI, commentsCv2.values());
 

				ContentsCreatedByContentValues contentsCv3 = new ContentsCreatedByContentValues();
				contentsCv3.putCreatedById(id);
				contentsCv3.putContentsId(extId);
				ctxt.getContentResolver().insert(ContentsCreatedByColumns.CONTENT_URI, contentsCv3.values());
 

				CreatedByFaqsContentValues faqsCv4 = new CreatedByFaqsContentValues();
				faqsCv4.putCreatedById(id);
				faqsCv4.putFaqsId(extId);
				ctxt.getContentResolver().insert(CreatedByFaqsColumns.CONTENT_URI, faqsCv4.values());
 

				CreatedByFaqPartsContentValues faqPartsCv5 = new CreatedByFaqPartsContentValues();
				faqPartsCv5.putCreatedById(id);
				faqPartsCv5.putFaqPartsId(extId);
				ctxt.getContentResolver().insert(CreatedByFaqPartsColumns.CONTENT_URI, faqPartsCv5.values());
 

				CreatedByFaqSectionsContentValues faqSectionsCv6 = new CreatedByFaqSectionsContentValues();
				faqSectionsCv6.putCreatedById(id);
				faqSectionsCv6.putFaqSectionsId(extId);
				ctxt.getContentResolver().insert(CreatedByFaqSectionsColumns.CONTENT_URI, faqSectionsCv6.values());
 

				CreatedByNewsContentValues newsCv7 = new CreatedByNewsContentValues();
				newsCv7.putCreatedById(id);
				newsCv7.putNewsId(extId);
				ctxt.getContentResolver().insert(CreatedByNewsColumns.CONTENT_URI, newsCv7.values());
 

				ActionTypeCreatedByContentValues actionTypeCv8 = new ActionTypeCreatedByContentValues();
				actionTypeCv8.putCreatedById(id);
				actionTypeCv8.putActionTypeId(extId);
				ctxt.getContentResolver().insert(ActionTypeCreatedByColumns.CONTENT_URI, actionTypeCv8.values());
 

				CreatedByTaxonomiesContentValues taxonomiesCv9 = new CreatedByTaxonomiesContentValues();
				taxonomiesCv9.putCreatedById(id);
				taxonomiesCv9.putTaxonomiesId(extId);
				ctxt.getContentResolver().insert(CreatedByTaxonomiesColumns.CONTENT_URI, taxonomiesCv9.values());
 

				CreatedByTermsContentValues termsCv10 = new CreatedByTermsContentValues();
				termsCv10.putCreatedById(id);
				termsCv10.putTermsId(extId);
				ctxt.getContentResolver().insert(CreatedByTermsColumns.CONTENT_URI, termsCv10.values());
 
				ctxt.getContentResolver().insert(CreatedByColumns.CONTENT_URI, val.values()); 
			list.add(val);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<AssignedKitchenWaresContentValues> parseJsonAssignedKitchenWares(JSONObject jo, Context ctxt) { 
		ArrayList<AssignedKitchenWaresContentValues> list = new ArrayList<AssignedKitchenWaresContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					AssignedKitchenWaresSelection where = new AssignedKitchenWaresSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			AssignedKitchenWaresContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new AssignedKitchenWaresContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("created_by") == false)
					val.putCreatedBy(Utils.extractIdFromUri(joInside.getString("created_by"))); 
				if( joInside.isNull("desc_nl") == false)
					val.putDescNl(joInside.getString("desc_nl")); 
				if( joInside.isNull("desc_fr") == false)
					val.putDescFr(joInside.getString("desc_fr")); 
				if( joInside.isNull("desc_es") == false)
					val.putDescEs(joInside.getString("desc_es")); 
				if( joInside.isNull("desc_de") == false)
					val.putDescDe(joInside.getString("desc_de")); 
				if( joInside.isNull("desc_it") == false)
					val.putDescIt(joInside.getString("desc_it")); 
				if( joInside.isNull("desc_en") == false)
					val.putDescEn(joInside.getString("desc_en")); 
				if( joInside.isNull("desc_ro") == false)
					val.putDescRo(joInside.getString("desc_ro")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("desc_bg") == false)
					val.putDescBg(joInside.getString("desc_bg")); 
				if( joInside.isNull("order") == false)
					val.putOrderDb(joInside.getInt("order")); 
				if( joInside.isNull("desc_ja") == false)
					val.putDescJa(joInside.getString("desc_ja")); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("desc_ru") == false)
					val.putDescRu(joInside.getString("desc_ru")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("desc_pt") == false)
					val.putDescPt(joInside.getString("desc_pt")); 
				if( joInside.isNull("desc_uk") == false)
					val.putDescUk(joInside.getString("desc_uk")); 
				if( joInside.isNull("step") == false)
					val.putStep(Utils.extractIdFromUri(joInside.getString("step"))); 

				if(joInside.isNull("kitchen_ware") == false)
					parseJsonKitchenWare(joInside.getJSONObject("kitchen_ware"), ctxt, id); 
				ctxt.getContentResolver().insert(AssignedKitchenWaresColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<KitchenWareContentValues> parseJsonKitchenWare(JSONObject joInside, Context ctxt, int extId) { 
		ArrayList<KitchenWareContentValues> list = new ArrayList<KitchenWareContentValues>(); 
		try { 
			KitchenWareContentValues val = new KitchenWareContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 

				AssignedKitchenWaresKitchenWareContentValues assignedKitchenWaresCv0 = new AssignedKitchenWaresKitchenWareContentValues();
				assignedKitchenWaresCv0.putKitchenWareId(id);
				assignedKitchenWaresCv0.putAssignedKitchenWaresId(extId);
				ctxt.getContentResolver().insert(AssignedKitchenWaresKitchenWareColumns.CONTENT_URI, assignedKitchenWaresCv0.values());
 
				if( joInside.isNull("name_en") == false)
					val.putNameEn(joInside.getString("name_en")); 
				if( joInside.isNull("name_fr") == false)
					val.putNameFr(joInside.getString("name_fr")); 
				if( joInside.isNull("associated_appliance") == false)
					val.putAssociatedAppliance(joInside.getString("associated_appliance")); 
				if( joInside.isNull("name_bg") == false)
					val.putNameBg(joInside.getString("name_bg")); 
				if( joInside.isNull("name_de") == false)
					val.putNameDe(joInside.getString("name_de")); 
				if( joInside.isNull("name_ja") == false)
					val.putNameJa(joInside.getString("name_ja")); 
				if( joInside.isNull("name_uk") == false)
					val.putNameUk(joInside.getString("name_uk")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("name_pt") == false)
					val.putNamePt(joInside.getString("name_pt")); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 
				if( joInside.isNull("name_ru") == false)
					val.putNameRu(joInside.getString("name_ru")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("name_es") == false)
					val.putNameEs(joInside.getString("name_es")); 
				if( joInside.isNull("name_nl") == false)
					val.putNameNl(joInside.getString("name_nl")); 
				if( joInside.isNull("name_it") == false)
					val.putNameIt(joInside.getString("name_it")); 
				if( joInside.isNull("name_ro") == false)
					val.putNameRo(joInside.getString("name_ro")); 

				if(joInside.isNull("created_by") == false)
					parseJsonCreatedBy(joInside.getJSONObject("created_by"), ctxt, id); 
				ctxt.getContentResolver().insert(KitchenWareColumns.CONTENT_URI, val.values()); 
			list.add(val);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<CommentsContentValues> parseJsonComments(JSONObject jo, Context ctxt) { 
		ArrayList<CommentsContentValues> list = new ArrayList<CommentsContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					CommentsSelection where = new CommentsSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			CommentsContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new CommentsContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("text") == false)
					val.putText(joInside.getString("text")); 
				if( joInside.isNull("resource") == false)
					val.putResource(Utils.extractIdFromUri(joInside.getString("resource"))); 
				if( joInside.isNull("state") == false)
					val.putState(joInside.getString("state")); 
				if( joInside.isNull("parent") == false)
					val.putParent(joInside.getString("parent")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("pk") == false)
					val.putPk(joInside.getInt("pk")); 

				if(joInside.isNull("created_by") == false)
					parseJsonCreatedBy(joInside.getJSONObject("created_by"), ctxt, id); 
				ctxt.getContentResolver().insert(CommentsColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<ContentsContentValues> parseJsonContents(JSONObject jo, Context ctxt) { 
		ArrayList<ContentsContentValues> list = new ArrayList<ContentsContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					ContentsSelection where = new ContentsSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			ContentsContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new ContentsContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("body") == false)
					val.putBody(joInside.getString("body")); 
				if( joInside.isNull("state") == false)
					val.putState(joInside.getString("state")); 
				if( joInside.isNull("lang") == false)
					val.putLang(joInside.getString("lang")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("title") == false)
					val.putTitle(joInside.getString("title")); 
				if( joInside.isNull("market") == false)
					val.putMarket(Utils.extractIdFromUri(joInside.getString("market"))); 

				JSONArray jArray;
				if( joInside.isNull("available_transitions") == false) {
					jArray = joInside.getJSONArray("available_transitions");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						String v = jArray.getString(j);
						AvailableTransitionsContentValues availableTransitionsCv0 = new AvailableTransitionsContentValues();
						availableTransitionsCv0.putContentsId(id);
						availableTransitionsCv0.putAvailableTransitions(v);
						ctxt.getContentResolver().insert(AvailableTransitionsColumns.CONTENT_URI, availableTransitionsCv0.values());
					}

				}
 
				if( joInside.isNull("order") == false)
					val.putOrderDb(joInside.getInt("order")); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 
				if( joInside.isNull("medias") == false) {
					jArray = joInside.getJSONArray("medias");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						ContentsMediasContentValues ContentsMediasCv2 = new ContentsMediasContentValues();
						ContentsMediasCv2.putContentsId(id);
						ContentsMediasCv2.putMediasId(v);
						ctxt.getContentResolver().insert(ContentsMediasColumns.CONTENT_URI, ContentsMediasCv2.values());
					}

				}
 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("content_type") == false)
					val.putContentType(joInside.getString("content_type")); 

				if(joInside.isNull("created_by") == false)
					parseJsonCreatedBy(joInside.getJSONObject("created_by"), ctxt, id); 
				ctxt.getContentResolver().insert(ContentsColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<FaqsContentValues> parseJsonFaqs(JSONObject jo, Context ctxt) { 
		ArrayList<FaqsContentValues> list = new ArrayList<FaqsContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					FaqsSelection where = new FaqsSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			FaqsContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new FaqsContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("body") == false)
					val.putBody(joInside.getString("body")); 
				if( joInside.isNull("state") == false)
					val.putState(joInside.getString("state")); 
				if( joInside.isNull("lang") == false)
					val.putLang(joInside.getString("lang")); 
				if( joInside.isNull("section") == false)
					val.putSection(Utils.extractIdFromUri(joInside.getString("section"))); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("title") == false)
					val.putTitle(joInside.getString("title")); 
				if( joInside.isNull("market") == false)
					val.putMarket(Utils.extractIdFromUri(joInside.getString("market"))); 

				JSONArray jArray;
				if( joInside.isNull("available_transitions") == false) {
					jArray = joInside.getJSONArray("available_transitions");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						String v = jArray.getString(j);
						AvailableTransitionsContentValues availableTransitionsCv0 = new AvailableTransitionsContentValues();
						availableTransitionsCv0.putContentsId(id);
						availableTransitionsCv0.putAvailableTransitions(v);
						ctxt.getContentResolver().insert(AvailableTransitionsColumns.CONTENT_URI, availableTransitionsCv0.values());
					}

				}
 
				if( joInside.isNull("order") == false)
					val.putOrderDb(joInside.getInt("order")); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 
				if( joInside.isNull("medias") == false) {
					jArray = joInside.getJSONArray("medias");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						FaqsMediasContentValues FaqsMediasCv2 = new FaqsMediasContentValues();
						FaqsMediasCv2.putFaqsId(id);
						FaqsMediasCv2.putMediasId(v);
						ctxt.getContentResolver().insert(FaqsMediasColumns.CONTENT_URI, FaqsMediasCv2.values());
					}

				}
 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 

				if(joInside.isNull("created_by") == false)
					parseJsonCreatedBy(joInside.getJSONObject("created_by"), ctxt, id); 
				ctxt.getContentResolver().insert(FaqsColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<FaqPartsContentValues> parseJsonFaqParts(JSONObject jo, Context ctxt) { 
		ArrayList<FaqPartsContentValues> list = new ArrayList<FaqPartsContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					FaqPartsSelection where = new FaqPartsSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			FaqPartsContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new FaqPartsContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("state") == false)
					val.putState(joInside.getString("state")); 
				if( joInside.isNull("lang") == false)
					val.putLang(joInside.getString("lang")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("title") == false)
					val.putTitle(joInside.getString("title")); 
				if( joInside.isNull("market") == false)
					val.putMarket(Utils.extractIdFromUri(joInside.getString("market"))); 

				JSONArray jArray;
				if( joInside.isNull("available_transitions") == false) {
					jArray = joInside.getJSONArray("available_transitions");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						String v = jArray.getString(j);
						AvailableTransitionsContentValues availableTransitionsCv1 = new AvailableTransitionsContentValues();
						availableTransitionsCv1.putContentsId(id);
						availableTransitionsCv1.putAvailableTransitions(v);
						ctxt.getContentResolver().insert(AvailableTransitionsColumns.CONTENT_URI, availableTransitionsCv1.values());
					}

				}
 
				if( joInside.isNull("order") == false)
					val.putOrderDb(joInside.getInt("order")); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 
				if( joInside.isNull("medias") == false) {
					jArray = joInside.getJSONArray("medias");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						FaqPartsMediasContentValues FaqPartsMediasCv3 = new FaqPartsMediasContentValues();
						FaqPartsMediasCv3.putFaqPartsId(id);
						FaqPartsMediasCv3.putMediasId(v);
						ctxt.getContentResolver().insert(FaqPartsMediasColumns.CONTENT_URI, FaqPartsMediasCv3.values());
					}

				}
 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 

				if(joInside.isNull("created_by") == false)
					parseJsonCreatedBy(joInside.getJSONObject("created_by"), ctxt, id); 
				ctxt.getContentResolver().insert(FaqPartsColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<FaqSectionsContentValues> parseJsonFaqSections(JSONObject jo, Context ctxt) { 
		ArrayList<FaqSectionsContentValues> list = new ArrayList<FaqSectionsContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					FaqSectionsSelection where = new FaqSectionsSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			FaqSectionsContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new FaqSectionsContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("state") == false)
					val.putState(joInside.getString("state")); 
				if( joInside.isNull("lang") == false)
					val.putLang(joInside.getString("lang")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("title") == false)
					val.putTitle(joInside.getString("title")); 
				if( joInside.isNull("market") == false)
					val.putMarket(Utils.extractIdFromUri(joInside.getString("market"))); 

				JSONArray jArray;
				if( joInside.isNull("available_transitions") == false) {
					jArray = joInside.getJSONArray("available_transitions");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						String v = jArray.getString(j);
						AvailableTransitionsContentValues availableTransitionsCv0 = new AvailableTransitionsContentValues();
						availableTransitionsCv0.putContentsId(id);
						availableTransitionsCv0.putAvailableTransitions(v);
						ctxt.getContentResolver().insert(AvailableTransitionsColumns.CONTENT_URI, availableTransitionsCv0.values());
					}

				}
 
				if( joInside.isNull("order") == false)
					val.putOrderDb(joInside.getInt("order")); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 
				if( joInside.isNull("medias") == false) {
					jArray = joInside.getJSONArray("medias");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						FaqSectionsMediasContentValues FaqSectionsMediasCv2 = new FaqSectionsMediasContentValues();
						FaqSectionsMediasCv2.putFaqSectionsId(id);
						FaqSectionsMediasCv2.putMediasId(v);
						ctxt.getContentResolver().insert(FaqSectionsMediasColumns.CONTENT_URI, FaqSectionsMediasCv2.values());
					}

				}
 
				if( joInside.isNull("faqs") == false) {
					jArray = joInside.getJSONArray("faqs");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						FaqSectionsFaqsContentValues FaqSectionsFaqsCv3 = new FaqSectionsFaqsContentValues();
						FaqSectionsFaqsCv3.putFaqSectionsId(id);
						FaqSectionsFaqsCv3.putFaqsId(v);
						ctxt.getContentResolver().insert(FaqSectionsFaqsColumns.CONTENT_URI, FaqSectionsFaqsCv3.values());
					}

				}
 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("part") == false)
					val.putPart(Utils.extractIdFromUri(joInside.getString("part"))); 

				if(joInside.isNull("created_by") == false)
					parseJsonCreatedBy(joInside.getJSONObject("created_by"), ctxt, id); 
				ctxt.getContentResolver().insert(FaqSectionsColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<MediasContentValues> parseJsonMedias(JSONObject jo, Context ctxt) { 
		ArrayList<MediasContentValues> list = new ArrayList<MediasContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					MediasSelection where = new MediasSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			MediasContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new MediasContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("thumbs") == false)
					val.putThumbs(joInside.getString("thumbs")); 

				JSONArray jArray;
				if( joInside.isNull("tags") == false) {
					jArray = joInside.getJSONArray("tags");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						String v = jArray.getString(j);
						TagsContentValues tagsCv0 = new TagsContentValues();
						tagsCv0.putMediasId(id);
						tagsCv0.putTags(v);
						ctxt.getContentResolver().insert(TagsColumns.CONTENT_URI, tagsCv0.values());
					}

				}
 
				if( joInside.isNull("title") == false)
					val.putTitle(joInside.getString("title")); 
				if( joInside.isNull("video_url") == false)
					val.putVideoUrl(joInside.getString("video_url")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("lang") == false)
					val.putLang(joInside.getString("lang")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				ctxt.getContentResolver().insert(MediasColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<NewsContentValues> parseJsonNews(JSONObject jo, Context ctxt) { 
		ArrayList<NewsContentValues> list = new ArrayList<NewsContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					NewsSelection where = new NewsSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			NewsContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new NewsContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("body") == false)
					val.putBody(joInside.getString("body")); 
				if( joInside.isNull("link") == false)
					val.putLink(joInside.getString("link")); 
				if( joInside.isNull("state") == false)
					val.putState(joInside.getString("state")); 
				if( joInside.isNull("resource") == false)
					val.putResource(Utils.extractIdFromUri(joInside.getString("resource"))); 
				if( joInside.isNull("type") == false)
					val.putType(joInside.getString("type")); 
				if( joInside.isNull("lang") == false)
					val.putLang(joInside.getString("lang")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("author") == false)
					val.putAuthor(joInside.getString("author")); 
				if( joInside.isNull("title") == false)
					val.putTitle(joInside.getString("title")); 
				if( joInside.isNull("end_date") == false)
					val.putEndDate(joInside.getString("end_date")); 
				if( joInside.isNull("market") == false)
					val.putMarket(Utils.extractIdFromUri(joInside.getString("market"))); 
				if( joInside.isNull("order") == false)
					val.putOrderDb(joInside.getInt("order")); 

				JSONArray jArray;
				if( joInside.isNull("available_transitions") == false) {
					jArray = joInside.getJSONArray("available_transitions");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						String v = jArray.getString(j);
						AvailableTransitionsContentValues availableTransitionsCv0 = new AvailableTransitionsContentValues();
						availableTransitionsCv0.putContentsId(id);
						availableTransitionsCv0.putAvailableTransitions(v);
						ctxt.getContentResolver().insert(AvailableTransitionsColumns.CONTENT_URI, availableTransitionsCv0.values());
					}

				}
 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("medias") == false) {
					jArray = joInside.getJSONArray("medias");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						MediasNewsContentValues MediasNewsCv2 = new MediasNewsContentValues();
						MediasNewsCv2.putNewsId(id);
						MediasNewsCv2.putMediasId(v);
						ctxt.getContentResolver().insert(MediasNewsColumns.CONTENT_URI, MediasNewsCv2.values());
					}

				}
 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("start_date") == false) 
					val.putStartDate(Utils.convertToDate(joInside.getString("start_date"), Utils.formats[7])); 
				if( joInside.isNull("publication_date") == false) 
					val.putPublicationDate(Utils.convertToDate(joInside.getString("publication_date"), Utils.formats[7])); 

				if(joInside.isNull("created_by") == false)
					parseJsonCreatedBy(joInside.getJSONObject("created_by"), ctxt, id);
				if(joInside.isNull("extra_data") == false)
					parseJsonExtraData(joInside.getJSONObject("extra_data"), ctxt, id); 
				ctxt.getContentResolver().insert(NewsColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<PacksContentValues> parseJsonPacks(JSONObject jo, Context ctxt) { 
		ArrayList<PacksContentValues> list = new ArrayList<PacksContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					PacksSelection where = new PacksSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			PacksContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new PacksContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("created_by") == false)
					val.putCreatedBy(Utils.extractIdFromUri(joInside.getString("created_by"))); 
				if( joInside.isNull("long_desc") == false)
					val.putLongDesc(joInside.getString("long_desc")); 
				if( joInside.isNull("store_available_ios") == false)
					val.putStoreAvailableIos(joInside.getBoolean("store_available_ios")); 
				if( joInside.isNull("store_product_id") == false)
					val.putStoreProductId(joInside.getString("store_product_id")); 
				if( joInside.isNull("desc") == false)
					val.putDesc(joInside.getString("desc")); 
				if( joInside.isNull("campaign_code") == false)
					val.putCampaignCode(joInside.getString("campaign_code")); 

				JSONArray jArray;
				if( joInside.isNull("recipes") == false) {
					jArray = joInside.getJSONArray("recipes");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						PacksRecipesContentValues PacksRecipesCv1 = new PacksRecipesContentValues();
						PacksRecipesCv1.putPacksId(id);
						PacksRecipesCv1.putRecipesId(v);
						ctxt.getContentResolver().insert(PacksRecipesColumns.CONTENT_URI, PacksRecipesCv1.values());
					}

				}
 
				if( joInside.isNull("state") == false)
					val.putState(joInside.getString("state")); 
				if( joInside.isNull("store_available_android") == false)
					val.putStoreAvailableAndroid(joInside.getBoolean("store_available_android")); 
				if( joInside.isNull("lang") == false)
					val.putLang(joInside.getString("lang")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("billing_method") == false)
					val.putBillingMethod(joInside.getString("billing_method")); 
				if( joInside.isNull("price") == false)
					val.putPrice(joInside.getInt("price")); 
				if( joInside.isNull("market") == false)
					val.putMarket(Utils.extractIdFromUri(joInside.getString("market"))); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("name") == false)
					val.putName(joInside.getString("name")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("campaign") == false)
					val.putCampaign(joInside.getString("campaign")); 
				if( joInside.isNull("currency_code") == false)
					val.putCurrencyCode(joInside.getString("currency_code")); 
				ctxt.getContentResolver().insert(PacksColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<RecipesContentValues> parseJsonRecipes(JSONObject jo, Context ctxt) { 
		ArrayList<RecipesContentValues> list = new ArrayList<RecipesContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					RecipesSelection where = new RecipesSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			RecipesContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new RecipesContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 

				JSONArray jArray;
				if( joInside.isNull("tags") == false) {
					jArray = joInside.getJSONArray("tags");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						String v = jArray.getString(j);
						TagsContentValues tagsCv0 = new TagsContentValues();
						tagsCv0.putMediasId(id);
						tagsCv0.putTags(v);
						ctxt.getContentResolver().insert(TagsColumns.CONTENT_URI, tagsCv0.values());
					}

				}
 
				if( joInside.isNull("created_by") == false)
					val.putCreatedBy(Utils.extractIdFromUri(joInside.getString("created_by"))); 
				if( joInside.isNull("source_uri") == false)
					val.putSourceUri(joInside.getString("source_uri")); 
				if( joInside.isNull("source_id") == false)
					val.putSourceId(joInside.getString("source_id")); 
				if( joInside.isNull("terms") == false) {
					jArray = joInside.getJSONArray("terms");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						RecipesTermsContentValues RecipesTermsCv2 = new RecipesTermsContentValues();
						RecipesTermsCv2.putRecipesId(id);
						RecipesTermsCv2.putTermsId(v);
						ctxt.getContentResolver().insert(RecipesTermsColumns.CONTENT_URI, RecipesTermsCv2.values());
					}

				}
 
				if( joInside.isNull("state") == false)
					val.putState(joInside.getString("state")); 
				if( joInside.isNull("short_name") == false)
					val.putShortName(joInside.getString("short_name")); 
				if( joInside.isNull("lang") == false)
					val.putLang(joInside.getString("lang")); 
				if( joInside.isNull("version") == false)
					val.putVersion(joInside.getString("version")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("packs") == false) {
					jArray = joInside.getJSONArray("packs");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						PacksRecipesContentValues PacksRecipesCv3 = new PacksRecipesContentValues();
						PacksRecipesCv3.putRecipesId(id);
						PacksRecipesCv3.putPacksId(v);
						ctxt.getContentResolver().insert(PacksRecipesColumns.CONTENT_URI, PacksRecipesCv3.values());
					}

				}
 
				if( joInside.isNull("market") == false)
					val.putMarket(Utils.extractIdFromUri(joInside.getString("market"))); 
				if( joInside.isNull("appliances") == false) {
					jArray = joInside.getJSONArray("appliances");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						AppliancesRecipesContentValues AppliancesRecipesCv4 = new AppliancesRecipesContentValues();
						AppliancesRecipesCv4.putRecipesId(id);
						AppliancesRecipesCv4.putAppliancesId(v);
						ctxt.getContentResolver().insert(AppliancesRecipesColumns.CONTENT_URI, AppliancesRecipesCv4.values());
					}

				}
 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 
				if( joInside.isNull("medias") == false) {
					jArray = joInside.getJSONArray("medias");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						MediasRecipesContentValues MediasRecipesCv6 = new MediasRecipesContentValues();
						MediasRecipesCv6.putRecipesId(id);
						MediasRecipesCv6.putMediasId(v);
						ctxt.getContentResolver().insert(MediasRecipesColumns.CONTENT_URI, MediasRecipesCv6.values());
					}

				}
 
				if( joInside.isNull("name") == false)
					val.putName(joInside.getString("name")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 

				if(joInside.isNull("binaries") == false)
					parseJsonBinaries(joInside.getJSONObject("binaries"), ctxt, id);
				if(joInside.isNull("rates") == false)
					parseJsonRates(joInside.getJSONObject("rates"), ctxt, id);
				if(joInside.isNull("variants") == false)
					parseJsonVariants(joInside.getJSONArray("variants"), ctxt, id); 
				ctxt.getContentResolver().insert(RecipesColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<RatesContentValues> parseJsonRates(JSONObject joInside, Context ctxt, int extId) { 
		ArrayList<RatesContentValues> list = new ArrayList<RatesContentValues>(); 
		try { 
			RatesContentValues val = new RatesContentValues(); 

				RatesRecipesContentValues recipesCv0 = new RatesRecipesContentValues();
				recipesCv0.putRatesId(id);
				recipesCv0.putRecipesId(extId);
				ctxt.getContentResolver().insert(RatesRecipesColumns.CONTENT_URI, recipesCv0.values());
 
				if( joInside.isNull("rate") == false)
					val.putRate(joInside.getInt("rate")); 
				if( joInside.isNull("count") == false)
					val.putCountDb(joInside.getInt("count")); 
				ctxt.getContentResolver().insert(RatesColumns.CONTENT_URI, val.values()); 
			list.add(val);
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<VariantsContentValues> parseJsonVariants(JSONArray joInput, Context ctxt, int extId) { 
		ArrayList<VariantsContentValues> list = new ArrayList<VariantsContentValues>(); 
		try { 
			VariantsContentValues val; 
			for (int i = 0; i < joInput.length(); i++) { 
				JSONObject joInside = joInput.getJSONObject(i);
				val = new VariantsContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 

				RecipesVariantsContentValues recipesCv0 = new RecipesVariantsContentValues();
				recipesCv0.putVariantsId(id);
				recipesCv0.putRecipesId(extId);
				ctxt.getContentResolver().insert(RecipesVariantsColumns.CONTENT_URI, recipesCv0.values());
 
				if( joInside.isNull("total_time") == false)
					val.putTotalTime(joInside.getInt("total_time")); 
				if( joInside.isNull("preparation_time") == false)
					val.putPreparationTime(joInside.getInt("preparation_time")); 
				if( joInside.isNull("cooking_time") == false)
					val.putCookingTime(joInside.getInt("cooking_time")); 
				if( joInside.isNull("yield_value") == false)
					val.putYieldValue(joInside.getInt("yield_value")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 

				if(joInside.isNull("simple_steps") == false)
					parseJsonSimpleSteps(joInside.getJSONArray("simple_steps"), ctxt, id);
				if(joInside.isNull("steps") == false)
					parseJsonSteps(joInside.getJSONArray("steps"), ctxt, id); 
				ctxt.getContentResolver().insert(VariantsColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<SimpleStepsContentValues> parseJsonSimpleSteps(JSONArray joInput, Context ctxt, int extId) { 
		ArrayList<SimpleStepsContentValues> list = new ArrayList<SimpleStepsContentValues>(); 
		try { 
			SimpleStepsContentValues val; 
			for (int i = 0; i < joInput.length(); i++) { 
				JSONObject joInside = joInput.getJSONObject(i);
				val = new SimpleStepsContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 

				SimpleStepsVariantsContentValues variantsCv0 = new SimpleStepsVariantsContentValues();
				variantsCv0.putSimpleStepsId(id);
				variantsCv0.putVariantsId(extId);
				ctxt.getContentResolver().insert(SimpleStepsVariantsColumns.CONTENT_URI, variantsCv0.values());
 
				if( joInside.isNull("desc_fr") == false)
					val.putDescFr(joInside.getString("desc_fr")); 
				if( joInside.isNull("order") == false)
					val.putOrderDb(joInside.getInt("order")); 
				if( joInside.isNull("simple") == false)
					val.putSimple(joInside.getBoolean("simple")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("variant") == false)
					val.putVariant(Utils.extractIdFromUri(joInside.getString("variant"))); 
				if( joInside.isNull("destination") == false)
					val.putDestination(joInside.getString("destination")); 
				ctxt.getContentResolver().insert(SimpleStepsColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<StepsContentValues> parseJsonSteps(JSONArray joInput, Context ctxt, int extId) { 
		ArrayList<StepsContentValues> list = new ArrayList<StepsContentValues>(); 
		try { 
			StepsContentValues val; 
			for (int i = 0; i < joInput.length(); i++) { 
				JSONObject joInside = joInput.getJSONObject(i);
				val = new StepsContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 

				StepsVariantsContentValues variantsCv0 = new StepsVariantsContentValues();
				variantsCv0.putStepsId(id);
				variantsCv0.putVariantsId(extId);
				ctxt.getContentResolver().insert(StepsVariantsColumns.CONTENT_URI, variantsCv0.values());
 

				JSONArray jArray;
				if( joInside.isNull("assigned_kitchen_wares") == false) {
					jArray = joInside.getJSONArray("assigned_kitchen_wares");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						AssignedKitchenWaresStepsContentValues AssignedKitchenWaresStepsCv2 = new AssignedKitchenWaresStepsContentValues();
						AssignedKitchenWaresStepsCv2.putStepsId(id);
						AssignedKitchenWaresStepsCv2.putAssignedKitchenWaresId(v);
						ctxt.getContentResolver().insert(AssignedKitchenWaresStepsColumns.CONTENT_URI, AssignedKitchenWaresStepsCv2.values());
					}

				}
 
				if( joInside.isNull("desc_fr") == false)
					val.putDescFr(joInside.getString("desc_fr")); 
				if( joInside.isNull("order") == false)
					val.putOrderDb(joInside.getInt("order")); 
				if( joInside.isNull("simple") == false)
					val.putSimple(joInside.getBoolean("simple")); 
				if( joInside.isNull("medias") == false) {
					jArray = joInside.getJSONArray("medias");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						MediasStepsContentValues MediasStepsCv3 = new MediasStepsContentValues();
						MediasStepsCv3.putStepsId(id);
						MediasStepsCv3.putMediasId(v);
						ctxt.getContentResolver().insert(MediasStepsColumns.CONTENT_URI, MediasStepsCv3.values());
					}

				}
 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("variant") == false)
					val.putVariant(Utils.extractIdFromUri(joInside.getString("variant"))); 
				if( joInside.isNull("destination") == false)
					val.putDestination(joInside.getString("destination")); 

				if(joInside.isNull("assigned_ingredients") == false)
					parseJsonAssignedIngredients(joInside.getJSONArray("assigned_ingredients"), ctxt, id);
				if(joInside.isNull("actions") == false)
					parseJsonActions(joInside.getJSONArray("actions"), ctxt, id); 
				ctxt.getContentResolver().insert(StepsColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<AssignedIngredientsContentValues> parseJsonAssignedIngredients(JSONArray joInput, Context ctxt, int extId) { 
		ArrayList<AssignedIngredientsContentValues> list = new ArrayList<AssignedIngredientsContentValues>(); 
		try { 
			AssignedIngredientsContentValues val; 
			for (int i = 0; i < joInput.length(); i++) { 
				JSONObject joInside = joInput.getJSONObject(i);
				val = new AssignedIngredientsContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 

				AssignedIngredientsStepsContentValues stepsCv0 = new AssignedIngredientsStepsContentValues();
				stepsCv0.putAssignedIngredientsId(id);
				stepsCv0.putStepsId(extId);
				ctxt.getContentResolver().insert(AssignedIngredientsStepsColumns.CONTENT_URI, stepsCv0.values());
 
				if( joInside.isNull("desc_fr") == false)
					val.putDescFr(joInside.getString("desc_fr")); 
				if( joInside.isNull("order") == false)
					val.putOrderDb(joInside.getInt("order")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("quantity_value") == false)
					val.putQuantityValue(joInside.getInt("quantity_value")); 
				if( joInside.isNull("step") == false)
					val.putStep(Utils.extractIdFromUri(joInside.getString("step"))); 
				ctxt.getContentResolver().insert(AssignedIngredientsColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<ActionsContentValues> parseJsonActions(JSONArray joInput, Context ctxt, int extId) { 
		ArrayList<ActionsContentValues> list = new ArrayList<ActionsContentValues>(); 
		try { 
			ActionsContentValues val; 
			for (int i = 0; i < joInput.length(); i++) { 
				JSONObject joInside = joInput.getJSONObject(i);
				val = new ActionsContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 

				ActionsStepsContentValues stepsCv0 = new ActionsStepsContentValues();
				stepsCv0.putActionsId(id);
				stepsCv0.putStepsId(extId);
				ctxt.getContentResolver().insert(ActionsStepsColumns.CONTENT_URI, stepsCv0.values());
 
				if( joInside.isNull("created_by") == false)
					val.putCreatedBy(Utils.extractIdFromUri(joInside.getString("created_by"))); 
				if( joInside.isNull("action_display") == false)
					val.putActionDisplay(joInside.getString("action_display")); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("is_default") == false)
					val.putIsDefault(joInside.getBoolean("is_default")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 

				if(joInside.isNull("action_type") == false)
					parseJsonActionType(joInside.getJSONArray("action_type"), ctxt, id); 
				ctxt.getContentResolver().insert(ActionsColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<TaxonomiesContentValues> parseJsonTaxonomies(JSONObject jo, Context ctxt) { 
		ArrayList<TaxonomiesContentValues> list = new ArrayList<TaxonomiesContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					TaxonomiesSelection where = new TaxonomiesSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			TaxonomiesContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new TaxonomiesContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("name_en") == false)
					val.putNameEn(joInside.getString("name_en")); 
				if( joInside.isNull("name_fr") == false)
					val.putNameFr(joInside.getString("name_fr")); 
				if( joInside.isNull("name_bg") == false)
					val.putNameBg(joInside.getString("name_bg")); 
				if( joInside.isNull("name_de") == false)
					val.putNameDe(joInside.getString("name_de")); 
				if( joInside.isNull("name_ja") == false)
					val.putNameJa(joInside.getString("name_ja")); 
				if( joInside.isNull("type") == false)
					val.putType(joInside.getString("type")); 
				if( joInside.isNull("name_uk") == false)
					val.putNameUk(joInside.getString("name_uk")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("name_pt") == false)
					val.putNamePt(joInside.getString("name_pt")); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 
				if( joInside.isNull("name_ru") == false)
					val.putNameRu(joInside.getString("name_ru")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("name_es") == false)
					val.putNameEs(joInside.getString("name_es")); 
				if( joInside.isNull("name_nl") == false)
					val.putNameNl(joInside.getString("name_nl")); 
				if( joInside.isNull("name_it") == false)
					val.putNameIt(joInside.getString("name_it")); 
				if( joInside.isNull("content_type") == false)
					val.putContentType(Utils.extractIdFromUri(joInside.getString("content_type"))); 
				if( joInside.isNull("name_ro") == false)
					val.putNameRo(joInside.getString("name_ro")); 

				if(joInside.isNull("created_by") == false)
					parseJsonCreatedBy(joInside.getJSONObject("created_by"), ctxt, id); 
				ctxt.getContentResolver().insert(TaxonomiesColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<TermsContentValues> parseJsonTerms(JSONObject jo, Context ctxt) { 
		ArrayList<TermsContentValues> list = new ArrayList<TermsContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					TermsSelection where = new TermsSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			TermsContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new TermsContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("enabled") == false)
					val.putEnabled(joInside.getBoolean("enabled")); 
				if( joInside.isNull("name_en") == false)
					val.putNameEn(joInside.getString("name_en")); 
				if( joInside.isNull("name_fr") == false)
					val.putNameFr(joInside.getString("name_fr")); 
				if( joInside.isNull("name_bg") == false)
					val.putNameBg(joInside.getString("name_bg")); 
				if( joInside.isNull("name_ja") == false)
					val.putNameJa(joInside.getString("name_ja")); 
				if( joInside.isNull("name_de") == false)
					val.putNameDe(joInside.getString("name_de")); 
				if( joInside.isNull("name_uk") == false)
					val.putNameUk(joInside.getString("name_uk")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("name_pt") == false)
					val.putNamePt(joInside.getString("name_pt")); 
				if( joInside.isNull("order") == false)
					val.putOrderDb(joInside.getInt("order")); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("modified_by") == false)
					val.putModifiedBy(Utils.extractIdFromUri(joInside.getString("modified_by"))); 

				JSONArray jArray;
				if( joInside.isNull("medias") == false) {
					jArray = joInside.getJSONArray("medias");
					for(int j = 0, count = jArray.length(); j< count; j++) {
						final int v = Utils.extractIdFromUri(jArray.getString(j));
						MediasTermsContentValues MediasTermsCv1 = new MediasTermsContentValues();
						MediasTermsCv1.putTermsId(id);
						MediasTermsCv1.putMediasId(v);
						ctxt.getContentResolver().insert(MediasTermsColumns.CONTENT_URI, MediasTermsCv1.values());
					}

				}
 
				if( joInside.isNull("name_ru") == false)
					val.putNameRu(joInside.getString("name_ru")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("name_es") == false)
					val.putNameEs(joInside.getString("name_es")); 
				if( joInside.isNull("name_nl") == false)
					val.putNameNl(joInside.getString("name_nl")); 
				if( joInside.isNull("taxonomy") == false)
					val.putTaxonomy(Utils.extractIdFromUri(joInside.getString("taxonomy"))); 
				if( joInside.isNull("name_it") == false)
					val.putNameIt(joInside.getString("name_it")); 
				if( joInside.isNull("name_ro") == false)
					val.putNameRo(joInside.getString("name_ro")); 

				if(joInside.isNull("created_by") == false)
					parseJsonCreatedBy(joInside.getJSONObject("created_by"), ctxt, id); 
				ctxt.getContentResolver().insert(TermsColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
	public static ArrayList<UnitsContentValues> parseJsonUnits(JSONObject jo, Context ctxt) { 
		ArrayList<UnitsContentValues> list = new ArrayList<UnitsContentValues>(); 
		try { 
			if (jo.isNull("deleted") == false) {
				JSONArray jObjects = jo.getJSONArray("deleted");
				for (int h = 0; h < jObjects.length(); h++) {
					UnitsSelection where = new UnitsSelection();
					where.idDb(jObjects.getInt(h));
					where.delete(ctxt.getContentResolver());
				}
			}
			UnitsContentValues val; 
			JSONArray jaObjects = jo.getJSONArray("objects"); 
			for (int i = 0; i < jaObjects.length(); i++) { 
				JSONObject joInside = jaObjects.getJSONObject(i);
				val = new UnitsContentValues(); 
				int id = 0;
				if( joInside.isNull("id") == false) {
					id = joInside.getInt("id");
					val.putIdDb(id);
				} 
				if( joInside.isNull("abbreviation_de") == false)
					val.putAbbreviationDe(joInside.getString("abbreviation_de")); 
				if( joInside.isNull("name_plural_es") == false)
					val.putNamePluralEs(joInside.getString("name_plural_es")); 
				if( joInside.isNull("abbreviation_fr") == false)
					val.putAbbreviationFr(joInside.getString("abbreviation_fr")); 
				if( joInside.isNull("name_bg") == false)
					val.putNameBg(joInside.getString("name_bg")); 
				if( joInside.isNull("name_de") == false)
					val.putNameDe(joInside.getString("name_de")); 
				if( joInside.isNull("name_ja") == false)
					val.putNameJa(joInside.getString("name_ja")); 
				if( joInside.isNull("type") == false)
					val.putType(joInside.getString("type")); 
				if( joInside.isNull("name_plural_en") == false)
					val.putNamePluralEn(joInside.getString("name_plural_en")); 
				if( joInside.isNull("name_uk") == false)
					val.putNameUk(joInside.getString("name_uk")); 
				if( joInside.isNull("name_plural_it") == false)
					val.putNamePluralIt(joInside.getString("name_plural_it")); 
				if( joInside.isNull("created") == false) 
					val.putCreated(Utils.convertToDate(joInside.getString("created"), Utils.formats[7])); 
				if( joInside.isNull("abbreviation_ja") == false)
					val.putAbbreviationJa(joInside.getString("abbreviation_ja")); 
				if( joInside.isNull("name_plural_ja") == false)
					val.putNamePluralJa(joInside.getString("name_plural_ja")); 
				if( joInside.isNull("abbreviation_bg") == false)
					val.putAbbreviationBg(joInside.getString("abbreviation_bg")); 
				if( joInside.isNull("abbreviation_uk") == false)
					val.putAbbreviationUk(joInside.getString("abbreviation_uk")); 
				if( joInside.isNull("name_es") == false)
					val.putNameEs(joInside.getString("name_es")); 
				if( joInside.isNull("name_nl") == false)
					val.putNameNl(joInside.getString("name_nl")); 
				if( joInside.isNull("name_plural_ro") == false)
					val.putNamePluralRo(joInside.getString("name_plural_ro")); 
				if( joInside.isNull("name_plural_pt") == false)
					val.putNamePluralPt(joInside.getString("name_plural_pt")); 
				if( joInside.isNull("abbreviation_nl") == false)
					val.putAbbreviationNl(joInside.getString("abbreviation_nl")); 
				if( joInside.isNull("name_en") == false)
					val.putNameEn(joInside.getString("name_en")); 
				if( joInside.isNull("abbreviation_it") == false)
					val.putAbbreviationIt(joInside.getString("abbreviation_it")); 
				if( joInside.isNull("name_fr") == false)
					val.putNameFr(joInside.getString("name_fr")); 
				if( joInside.isNull("name_plural_nl") == false)
					val.putNamePluralNl(joInside.getString("name_plural_nl")); 
				if( joInside.isNull("abbreviation_en") == false)
					val.putAbbreviationEn(joInside.getString("abbreviation_en")); 
				if( joInside.isNull("name_plural_fr") == false)
					val.putNamePluralFr(joInside.getString("name_plural_fr")); 
				if( joInside.isNull("abbreviation_es") == false)
					val.putAbbreviationEs(joInside.getString("abbreviation_es")); 
				if( joInside.isNull("abbreviation_ro") == false)
					val.putAbbreviationRo(joInside.getString("abbreviation_ro")); 
				if( joInside.isNull("name_plural_ru") == false)
					val.putNamePluralRu(joInside.getString("name_plural_ru")); 
				if( joInside.isNull("modified") == false) 
					val.putModified(Utils.convertToDate(joInside.getString("modified"), Utils.formats[7])); 
				if( joInside.isNull("name_pt") == false)
					val.putNamePt(joInside.getString("name_pt")); 
				if( joInside.isNull("abbreviation_pt") == false)
					val.putAbbreviationPt(joInside.getString("abbreviation_pt")); 
				if( joInside.isNull("abbreviation_ru") == false)
					val.putAbbreviationRu(joInside.getString("abbreviation_ru")); 
				if( joInside.isNull("name_ru") == false)
					val.putNameRu(joInside.getString("name_ru")); 
				if( joInside.isNull("resource_uri") == false)
					val.putResourceUri(Utils.extractIdFromUri(joInside.getString("resource_uri"))); 
				if( joInside.isNull("name_plural_bg") == false)
					val.putNamePluralBg(joInside.getString("name_plural_bg")); 
				if( joInside.isNull("name_plural_de") == false)
					val.putNamePluralDe(joInside.getString("name_plural_de")); 
				if( joInside.isNull("name_plural_uk") == false)
					val.putNamePluralUk(joInside.getString("name_plural_uk")); 
				if( joInside.isNull("name_it") == false)
					val.putNameIt(joInside.getString("name_it")); 
				if( joInside.isNull("name_ro") == false)
					val.putNameRo(joInside.getString("name_ro")); 
				ctxt.getContentResolver().insert(UnitsColumns.CONTENT_URI, val.values()); 
				list.add(val);
			} 
		} catch (JSONException e) {
			e.printStackTrace();
		} 
		return list;
	}
 
